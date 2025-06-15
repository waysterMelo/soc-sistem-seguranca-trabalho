package com.ocupacional.soc.Services.impl;


import com.ocupacional.soc.Dto.Medicina.Aso.AsoExameRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Entities.Medicina.Aso.AsoEntity;
import com.ocupacional.soc.Entities.Medicina.Aso.AsoExameEntity;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.Aso.AsoMapper;
import com.ocupacional.soc.Repositories.Cadastros.ExameCatalogoRepository;
import com.ocupacional.soc.Repositories.Cadastros.ProfissionalRegistrosRepository;
import com.ocupacional.soc.Repositories.Cadastros.RiscoCatalogoRepository;
import com.ocupacional.soc.Repositories.Medicina.Aso.AsoExameRepository;
import com.ocupacional.soc.Repositories.Medicina.Aso.AsoRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Services.Aparelhos.FileStorageService;
import com.ocupacional.soc.Services.Medicina.Aso.AsoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AsoServiceImpl implements AsoService {

    private final AsoRepository asoRepository;
    private final AsoExameRepository asoExameRepository;
    private final ProfissionalRegistrosRepository registroProfissionalRepository;
    private final PrestadorServicoRepository prestadorRepository;
    private final RiscoCatalogoRepository riscoRepository;
    private final ExameCatalogoRepository exameRepository;
    private final AsoMapper asoMapper;
    private final FileStorageService fileStorageService;


    @Override
    public Page<AsoListDTO> findAll(Pageable pageable, String search) {
        String searchTerm = (search == null || search.isBlank()) ? "" : search;

        Page<AsoEntity> asoEntityPage = asoRepository.findBySearchTerm(searchTerm,  pageable);

        return asoEntityPage.map(asoMapper::toListDto);
    }

    @Override
    @Transactional
    public AsoResponseDTO findById(Long id) {
        AsoEntity entity = asoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ASO não encontrado com ID: " + id));
        return asoMapper.toResponseDto(entity);
    }

    @Override
    @Transactional
    public AsoResponseDTO create(AsoRequestDTO dto, List<MultipartFile> exameResultados) {
        // Valida se a quantidade de exames no DTO corresponde à de arquivos, se houver
        validateExamesAndFiles(dto.getExames(), exameResultados);

        AsoEntity entity = new AsoEntity();
        mapToEntity(entity, dto);

        // Salva a entidade ASO principal para obter um ID
        AsoEntity savedAso = asoRepository.save(entity);

        // Processa e salva os exames associados com seus respectivos arquivos
        if (!CollectionUtils.isEmpty(dto.getExames())) {
            List<AsoExameEntity> exames = processExames(dto.getExames(), exameResultados, savedAso);
            savedAso.getExames().addAll(exames);
        }

        return asoMapper.toResponseDto(savedAso);
    }

    @Override
    @Transactional
    public AsoResponseDTO update(Long id, AsoRequestDTO dto, List<MultipartFile> exameResultados) {
        validateExamesAndFiles(dto.getExames(), exameResultados);

        AsoEntity entity = asoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ASO não encontrado com ID: " + id));

        // Limpa as associações de exames antigos e seus arquivos
        entity.getExames().forEach(exame -> fileStorageService.deleteFile(exame.getResultadoUrl()));
        entity.getExames().clear();
        asoRepository.flush(); // Força a remoção dos órfãos antes de adicionar novos

        // Mapeia os dados principais e os riscos
        mapToEntity(entity, dto);

        // Processa e salva os novos exames
        if (!CollectionUtils.isEmpty(dto.getExames())) {
            List<AsoExameEntity> exames = processExames(dto.getExames(), exameResultados, entity);
            entity.getExames().addAll(exames);
        }

        AsoEntity updatedAso = asoRepository.save(entity);
        return asoMapper.toResponseDto(updatedAso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        AsoEntity entity = asoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ASO não encontrado com ID: " + id));

        // Deleta os arquivos de resultado associados antes da exclusão lógica
        entity.getExames().forEach(exame -> fileStorageService.deleteFile(exame.getResultadoUrl()));

        asoRepository.delete(entity);
    }


    private void mapToEntity(AsoEntity entity, AsoRequestDTO dto) {
        ProfissionalRegistrosEntity registro = registroProfissionalRepository.findById(dto.getRegistroProfissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Registro Profissional não encontrado com ID: " + dto.getRegistroProfissionalId()));

        PrestadorServicoEntity medicoExaminador = prestadorRepository.findById(dto.getMedicoExaminadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico Examinador não encontrado com ID: " + dto.getMedicoExaminadorId()));

        PrestadorServicoEntity medicoResponsavel = prestadorRepository.findById(dto.getMedicoResponsavelPcmsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico Responsável pelo PCMSO não encontrado com ID: " + dto.getMedicoResponsavelPcmsoId()));

        entity.setRegistroProfissional(registro);
        entity.setMedicoExaminador(medicoExaminador);
        entity.setMedicoResponsavelPcmso(medicoResponsavel);
        entity.setTipoRetificacao(dto.getTipoRetificacao());
        entity.setDataAsoRetificado(dto.getDataAsoRetificado());
        entity.setTipoAso(dto.getTipoAso());
        entity.setDataEmissao(dto.getDataEmissao());
        entity.setConclusaoAso(dto.getConclusaoAso());
        entity.setObservacoes(dto.getObservacoes());
        entity.setConclusaoColaborador(dto.getConclusaoColaborador());

        // Mapeia a lista de riscos
        if (!CollectionUtils.isEmpty(dto.getRiscoIds())) {
            Set<RiscoCatalogoEntity> riscos = new HashSet<>(riscoRepository.findAllById(dto.getRiscoIds()));
            entity.setRiscos(riscos);
        } else {
            entity.getRiscos().clear();
        }
    }


    private List<AsoExameEntity> processExames(List<AsoExameRequestDTO> exameDtos, List<MultipartFile> arquivos, AsoEntity aso) {
        List<AsoExameEntity> asoExames = new ArrayList<>();
        for (int i = 0; i < exameDtos.size(); i++) {
            AsoExameRequestDTO exameDto = exameDtos.get(i);
            MultipartFile arquivo = (arquivos != null && i < arquivos.size()) ? arquivos.get(i) : null;

            ExameCatalogoEntity exameCatalogo = exameRepository.findById(exameDto.getExameCatalogoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Exame do Catálogo não encontrado com ID: " + exameDto.getExameCatalogoId()));

            String arquivoUrl = null;
            if (arquivo != null && !arquivo.isEmpty()) {
                arquivoUrl = fileStorageService.storeFile(arquivo);
            }

            AsoExameEntity asoExame = AsoExameEntity.builder()
                    .aso(aso)
                    .exame(exameCatalogo)
                    .resultadoUrl(arquivoUrl)
                    .build();
            asoExames.add(asoExame);
        }
        return asoExameRepository.saveAll(asoExames);
    }


    private void validateExamesAndFiles(List<AsoExameRequestDTO> exames, List<MultipartFile> arquivos) {
        int examesCount = exames == null ? 0 : exames.size();
        int arquivosCount = arquivos == null ? 0 : arquivos.size();

        if (examesCount != arquivosCount && arquivosCount != 0) {
            throw new BusinessException("A quantidade de exames (" + examesCount + ") não corresponde à quantidade de arquivos de resultado enviados (" + arquivosCount + ").");
        }
    }
}