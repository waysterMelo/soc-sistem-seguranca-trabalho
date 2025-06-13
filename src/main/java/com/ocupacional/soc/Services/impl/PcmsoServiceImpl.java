package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.RiscoTrabalhistaPgrResponseDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoExameRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoListDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Pcmso.PcmsoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Pcmso.PcmsoExameEntity;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.Pcmso.PcmsoStatus;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.RiscoTrabalhistaPgrMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Pcmso.PcmsoMapper;
import com.ocupacional.soc.Repositories.Cadastros.ExameCatalogoRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncaoRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Pcmso.PcmsoExameRepository;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Pcmso.PcmsoRepository;
import com.ocupacional.soc.Services.Aparelhos.FileStorageService;
import com.ocupacional.soc.Services.SegurancaTrabalho.Pcmso.PcmsoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PcmsoServiceImpl implements PcmsoService {

    private final PcmsoRepository pcmsoRepository;
    private final UnidadeOperacionalRepository unidadeRepository;
    private final FuncaoRepository funcaoRepository;
    private final ExameCatalogoRepository exameCatalogoRepository;
    private final PcmsoMapper pcmsoMapper;
    private final RiscoTrabalhistaPgrMapper riscoTrabalhistaPgrMapper;
    private final FileStorageService fileStorageService;
    private final PcmsoExameRepository pcmsoExameRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<PcmsoListDTO> findAll(Pageable pageable, String search) {
        String searchTerm = (search == null || search.isBlank()) ? "" : search;
        return pcmsoRepository.findBySearchTerm(searchTerm, pageable).map(pcmsoMapper::toListDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PcmsoResponseDTO findById(Long id) {
        PcmsoEntity pcmsoEntity = pcmsoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PCMSO não encontrado com ID: " + id));

        PcmsoResponseDTO responseDTO = pcmsoMapper.toResponseDto(pcmsoEntity);

        if (responseDTO != null && !CollectionUtils.isEmpty(pcmsoEntity.getExames())) {
            List<Long> funcaoIds = pcmsoEntity.getExames().stream()
                    .map(pcmsoExame -> pcmsoExame.getFuncao().getId())
                    .distinct()
                    .collect(Collectors.toList());

            List<FuncaoEntity> funcoes = funcaoRepository.findAllById(funcaoIds);

            List<RiscoTrabalhistaPgrResponseDTO> riscos = funcoes.stream()
                    .flatMap(funcao -> funcao.getRiscosPGR().stream())
                    .map(riscoTrabalhistaPgrMapper::toResponseDTO)
                    .collect(Collectors.toList());

            responseDTO.setRiscos(riscos);
        }

        return responseDTO;
    }

    @Override
    @Transactional
    public PcmsoResponseDTO create(PcmsoRequestDTO dto, MultipartFile capaImagem) {
        validateActivePcmso(dto.getUnidadeOperacionalId(), dto.getStatus(), null);

        UnidadeOperacionalEntity unidade = unidadeRepository.findById(dto.getUnidadeOperacionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Unidade Operacional não encontrada com ID: " + dto.getUnidadeOperacionalId()));

        // 1. Cria e salva a entidade PCMSO principal PRIMEIRO
        PcmsoEntity entity = new PcmsoEntity();
        mapPcmsoFields(entity, dto, unidade);
        if (capaImagem != null && !capaImagem.isEmpty()) {
            entity.setCapaImagemUrl(fileStorageService.storeFile(capaImagem));
        }
        PcmsoEntity savedPcmso = pcmsoRepository.save(entity);

        // 2. Cria e salva as entidades de Exame, associando ao PCMSO já salvo
        if (!CollectionUtils.isEmpty(dto.getExames())) {
            List<PcmsoExameEntity> exames = mapExamesFromDto(dto, savedPcmso);
            pcmsoExameRepository.saveAll(exames); // Salva a lista de exames explicitamente
        }

        return findById(savedPcmso.getId());
    }

    @Override
    @Transactional
    public PcmsoResponseDTO update(Long id, PcmsoRequestDTO dto, MultipartFile capaImagem) {
        PcmsoEntity entity = pcmsoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PCMSO não encontrado com ID: " + id));

        validateActivePcmso(dto.getUnidadeOperacionalId(), dto.getStatus(), id);

        // Limpa os exames antigos
        entity.getExames().clear();
        pcmsoRepository.saveAndFlush(entity); // Força a remoção dos órfãos

        // Mapeia os dados do PCMSO
        UnidadeOperacionalEntity unidade = unidadeRepository.findById(dto.getUnidadeOperacionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Unidade Operacional não encontrada com ID: " + dto.getUnidadeOperacionalId()));
        mapPcmsoFields(entity, dto, unidade);
        if (capaImagem != null && !capaImagem.isEmpty()) {
            fileStorageService.deleteFile(entity.getCapaImagemUrl());
            entity.setCapaImagemUrl(fileStorageService.storeFile(capaImagem));
        }
        PcmsoEntity updatedPcmso = pcmsoRepository.save(entity);

        // Cria e salva as novas associações de exame
        if (!CollectionUtils.isEmpty(dto.getExames())) {
            List<PcmsoExameEntity> exames = mapExamesFromDto(dto, updatedPcmso);
            pcmsoExameRepository.saveAll(exames);
        }

        return findById(updatedPcmso.getId());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        PcmsoEntity entity = pcmsoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PCMSO não encontrado com ID: " + id));

        fileStorageService.deleteFile(entity.getCapaImagemUrl());
        pcmsoRepository.delete(entity);
    }


    private void validateActivePcmso(Long unidadeId, PcmsoStatus status, Long currentPcmsoId) {
        if (status == PcmsoStatus.ATIVO) {
            boolean exists;
            if (currentPcmsoId == null) {
                exists = pcmsoRepository.existsByUnidadeOperacionalIdAndStatus(unidadeId, PcmsoStatus.ATIVO);
            } else {
                exists = pcmsoRepository.existsByUnidadeOperacionalIdAndStatusAndIdNot(unidadeId, PcmsoStatus.ATIVO, currentPcmsoId);
            }
            if (exists) {
                throw new BusinessException("Já existe um PCMSO ATIVO para esta Unidade Operacional.");
            }
        }
    }

    private List<PcmsoExameEntity> mapExamesFromDto(PcmsoRequestDTO dto, PcmsoEntity pcmso) {
        List<PcmsoExameEntity> result = new ArrayList<>();

        for (PcmsoExameRequestDTO e : dto.getExames()) {
            FuncaoEntity funcao = funcaoRepository.findById(e.getFuncaoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Função não encontrada"));

            for (Long exameId : e.getExameId()) {
                ExameCatalogoEntity exame = exameCatalogoRepository.findById(exameId)
                        .orElseThrow(() -> new ResourceNotFoundException("Exame não encontrado"));

                result.add(PcmsoExameEntity.builder()
                        .pcmso(pcmso)
                        .funcao(funcao)
                        .exame(exame)
                        .tipoExame(e.getTipoExame())
                        .periodicidadeMeses(e.getPeriodicidadeMeses())
                        .build());
            }
        }
        return result;
    }

    private void mapPcmsoFields(PcmsoEntity entity, PcmsoRequestDTO dto, UnidadeOperacionalEntity unidade) {
        entity.setUnidadeOperacional(unidade);
        entity.setStatus(dto.getStatus());
        entity.setDataDocumento(dto.getDataDocumento());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setCapa(dto.getCapa());
        entity.setIntroducao(dto.getIntroducao());
        entity.setSobrePcmso(dto.getSobrePcmso());
        entity.setConclusao(dto.getConclusao());
    }
}