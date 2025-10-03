package com.ocupacional.soc.Services.impl;


import com.ocupacional.soc.Dto.Medicina.Aso.AsoExameRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Entities.Medicina.Aso.AsoEntity;
import com.ocupacional.soc.Entities.Medicina.Aso.AsoExameEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.Aso.AsoMapper;
import com.ocupacional.soc.Repositories.Cadastros.ExameCatalogoRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncionarioRepository;
import com.ocupacional.soc.Repositories.Cadastros.RiscoCatalogoRepository;
import com.ocupacional.soc.Repositories.Medicina.Aso.AsoExameRepository;
import com.ocupacional.soc.Repositories.Medicina.Aso.AsoRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Services.Medicina.Aso.AsoFileStorageService;
import com.ocupacional.soc.Services.Medicina.Aso.AsoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsoServiceImpl implements AsoService {

    private final AsoRepository asoRepository;
    private final AsoExameRepository asoExameRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PrestadorServicoRepository prestadorRepository;
    private final RiscoCatalogoRepository riscoRepository;
    private final ExameCatalogoRepository exameRepository;
    private final AsoMapper asoMapper;
    private final AsoFileStorageService asoFileStorageService;



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
    public List<AsoListDTO> findByFuncionarioId(Long funcionarioId) {
        List<AsoEntity> asoEntities = asoRepository.findByFuncionarioId(funcionarioId);
        return asoEntities.stream().map(asoMapper::toListDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AsoResponseDTO create(AsoRequestDTO dto, List<MultipartFile> files) {
        AsoEntity entity = new AsoEntity();
        mapToEntity(entity, dto);

        AsoEntity savedAso = asoRepository.save(entity);

        if (!CollectionUtils.isEmpty(dto.getExames())) {
            Map<String, MultipartFile> fileMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(files)) {
                fileMap = files.stream()
                        .collect(Collectors.toMap(MultipartFile::getOriginalFilename, Function.identity()));
            }
            List<AsoExameEntity> exames = processExames(dto.getExames(), savedAso, fileMap);
            savedAso.getExames().addAll(exames);
        }

        return asoMapper.toResponseDto(savedAso);
    }


    @Override
    @Transactional
    public AsoResponseDTO update(Long id, AsoRequestDTO dto) {
        AsoEntity entity = asoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ASO não encontrado com ID: " + id));

        entity.getExames().clear();
        asoRepository.flush(); 

        mapToEntity(entity, dto);

        if (!CollectionUtils.isEmpty(dto.getExames())) {
            // A lógica de update não suporta upload de novos arquivos, apenas re-associação.
            // Para suportar upload no update, uma lógica similar à do create (com multipart) seria necessária no controller e aqui.
            List<AsoExameEntity> exames = processExames(dto.getExames(), entity, Collections.emptyMap());
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

        asoRepository.delete(entity);
    }


    private void mapToEntity(AsoEntity entity, AsoRequestDTO dto) {
        FuncionarioEntity funcionario = funcionarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + dto.getFuncionarioId()));

        PrestadorServicoEntity medicoExaminador = prestadorRepository.findById(dto.getMedicoExaminadorId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico Examinador não encontrado com ID: " + dto.getMedicoExaminadorId()));

        PrestadorServicoEntity medicoResponsavel = prestadorRepository.findById(dto.getMedicoResponsavelPcmsoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico Responsável pelo PCMSO não encontrado com ID: " + dto.getMedicoResponsavelPcmsoId()));

        entity.setFuncionario(funcionario);
        entity.setMedicoExaminador(medicoExaminador);
        entity.setMedicoResponsavelPcmso(medicoResponsavel);
        entity.setTipoRetificacao(dto.getTipoRetificacao());
        entity.setDataAsoRetificado(dto.getDataAsoRetificado());
        entity.setTipoAso(dto.getTipoAso());
        entity.setDataEmissao(dto.getDataEmissao());
        entity.setConclusaoAso(dto.getConclusaoAso());
        entity.setObservacoes(dto.getObservacoes());
        entity.setConclusaoColaborador(dto.getConclusaoColaborador());
        entity.setDiasInapto(dto.getDiasInapto());
        entity.setStatus(dto.getStatus());
        entity.setNaoInformar(dto.getNaoInformar());

        if (!CollectionUtils.isEmpty(dto.getRiscoIds())) {
            Set<RiscoCatalogoEntity> riscos = new HashSet<>(riscoRepository.findAllById(dto.getRiscoIds()));
            entity.setRiscos(riscos);
        } else {
            entity.getRiscos().clear();
        }
    }


    private List<AsoExameEntity> processExames(List<AsoExameRequestDTO> exameDtos, AsoEntity aso, Map<String, MultipartFile> fileMap) {
        List<AsoExameEntity> asoExames = new ArrayList<>();
        for (AsoExameRequestDTO exameDto : exameDtos) {
            ExameCatalogoEntity exameCatalogo = exameRepository.findById(exameDto.getExameCatalogoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Exame do Catálogo não encontrado com ID: " + exameDto.getExameCatalogoId()));

            AsoExameEntity.AsoExameEntityBuilder asoExameBuilder = AsoExameEntity.builder()
                    .aso(aso)
                    .exame(exameCatalogo);

            if (exameDto.getNomeArquivoOriginal() != null && fileMap.containsKey(exameDto.getNomeArquivoOriginal())) {
                MultipartFile file = fileMap.get(exameDto.getNomeArquivoOriginal());
                String fileUrl = asoFileStorageService.storeFile(file);
                asoExameBuilder.resultadoUrl(fileUrl);
            }

            asoExames.add(asoExameBuilder.build());
        }
        return asoExameRepository.saveAll(asoExames);
    }
}
