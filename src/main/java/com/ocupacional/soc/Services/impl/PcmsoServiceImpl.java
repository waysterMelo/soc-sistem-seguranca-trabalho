package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.RiscoTrabalhistaPgrResponseDTO;
import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoExameRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Entities.Medicina.Pcmso.PcmsoEntity;
import com.ocupacional.soc.Entities.Medicina.Pcmso.PcmsoExameEntity;
import com.ocupacional.soc.Enuns.Medicina.Pcmso.PcmsoStatus;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.RiscoTrabalhistaPgrMapper;
import com.ocupacional.soc.Mapper.Medicina.Pcmso.PcmsoMapper;
import com.ocupacional.soc.Repositories.Cadastros.ExameCatalogoRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncaoRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Repositories.Medicina.Pcmso.PcmsoExameRepository;
import com.ocupacional.soc.Repositories.Medicina.Pcmso.PcmsoRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Services.Medicina.Pcmso.PcmsoFileStorageService;
import com.ocupacional.soc.Services.Medicina.Pcmso.PcmsoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PcmsoServiceImpl implements PcmsoService {

    private final PcmsoRepository pcmsoRepository;
    private final UnidadeOperacionalRepository unidadeRepository;
    private final FuncaoRepository funcaoRepository;
    private final ExameCatalogoRepository exameCatalogoRepository;
    private final PrestadorServicoRepository prestadorRepository;
    private final PcmsoMapper pcmsoMapper;
    private final RiscoTrabalhistaPgrMapper riscoTrabalhistaPgrMapper;
    private final PcmsoFileStorageService fileStorageService;
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

        PcmsoEntity entity = new PcmsoEntity();
        mapToEntity(entity, dto); // Lógica de mapeamento centralizada

        if (capaImagem != null && !capaImagem.isEmpty()) {
            entity.setImagemCapa(fileStorageService.storeFile(capaImagem));
        }

        PcmsoEntity savedPcmso = pcmsoRepository.save(entity);

        if (!CollectionUtils.isEmpty(dto.getExames())) {
            List<PcmsoExameEntity> exames = mapExamesFromDto(dto, savedPcmso);
            pcmsoExameRepository.saveAll(exames);
        }

        return findById(savedPcmso.getId());
    }

    @Override
    @Transactional
    public PcmsoResponseDTO update(Long id, PcmsoRequestDTO dto, MultipartFile capaImagem) {
        PcmsoEntity entity = pcmsoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PCMSO não encontrado com ID: " + id));

        validateActivePcmso(dto.getUnidadeOperacionalId(), dto.getStatus(), id);

        entity.getExames().clear();
        pcmsoRepository.saveAndFlush(entity);

        mapToEntity(entity, dto); // Lógica de mapeamento centralizada

        if (capaImagem != null && !capaImagem.isEmpty()) {
            fileStorageService.deleteFile(entity.getImagemCapa());
            entity.setImagemCapa(fileStorageService.storeFile(capaImagem));
        }

        PcmsoEntity updatedPcmso = pcmsoRepository.save(entity);

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

        // Deleta os exames associados ao PCMSO
        if (entity.getExames() != null && !entity.getExames().isEmpty()) {
            pcmsoExameRepository.deleteAll(entity.getExames());
        }

        // Limpa a coleção de elaboradores para remover as associações na tabela de junção
        entity.getElaboradores().clear();

        // Deleta o arquivo de imagem da capa, se existir
        if (entity.getImagemCapa() != null && !entity.getImagemCapa().isEmpty()) {
            fileStorageService.deleteFile(entity.getImagemCapa());
        }

        // Finalmente, deleta a entidade PCMSO
        pcmsoRepository.delete(entity);
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

    private void mapToEntity(PcmsoEntity entity, PcmsoRequestDTO dto) {
        UnidadeOperacionalEntity unidade = unidadeRepository.findById(dto.getUnidadeOperacionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Unidade Operacional não encontrada com ID: " + dto.getUnidadeOperacionalId()));

        PrestadorServicoEntity medicoResponsavel = prestadorRepository.findById(dto.getMedicoResponsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico Responsável não encontrado com ID: " + dto.getMedicoResponsavelId()));

        entity.setUnidadeOperacional(unidade);
        entity.setMedicoResponsavel(medicoResponsavel);
        entity.setStatus(dto.getStatus());
        entity.setDataDocumento(dto.getDataDocumento());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setCapa(dto.getCapa());
        entity.setIntroducao(dto.getIntroducao());
        entity.setSobrePcmso(dto.getSobrePcmso());
        entity.setConclusao(dto.getConclusao());

        // Mapeamento dos elaboradores
        entity.getElaboradores().clear();
        if (!CollectionUtils.isEmpty(dto.getElaboradoresIds())) {
            Set<PrestadorServicoEntity> elaboradores = new HashSet<>(prestadorRepository.findAllById(dto.getElaboradoresIds()));
            if(elaboradores.size() != dto.getElaboradoresIds().size()){
                throw new ResourceNotFoundException("Um ou mais elaboradores não foram encontrados.");
            }
            entity.setElaboradores(elaboradores);
        }
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
}