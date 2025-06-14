package com.ocupacional.soc.Services.impl;


import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoListDTO;
import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Entities.Medicina.PcmsoAnalitico.PcmsoAnaliticoEntity;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.PcmsoAnalitico.PcmsoAnaliticoMapper;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import com.ocupacional.soc.Repositories.Cadastros.UnidadeOperacionalRepository;
import com.ocupacional.soc.Repositories.Medicina.PcmsoAnalitico.PcmsoAnaliticoRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Services.Medicina.PcmsoAnalitico.PcmsoAnaliticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PcmsoAnaliticoServiceImpl implements PcmsoAnaliticoService {

    private final PcmsoAnaliticoRepository repository;
    private final EmpresaRepository empresaRepository;
    private final UnidadeOperacionalRepository unidadeRepository;
    private final PrestadorServicoRepository prestadorRepository;
    private final PcmsoAnaliticoMapper pcmsoAnaliticoMapper;

    @Override
    @Transactional
    public PcmsoAnaliticoResponseDTO create(PcmsoAnaliticoRequestDTO dto) {
        validatePcmso(dto, null);
        PcmsoAnaliticoEntity entity = new PcmsoAnaliticoEntity();
        mapToEntity(entity, dto);
        return pcmsoAnaliticoMapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional
    public PcmsoAnaliticoResponseDTO update(Long id, PcmsoAnaliticoRequestDTO dto) {
        PcmsoAnaliticoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PCMSO Analítico não encontrado com ID: " + id));
        validatePcmso(dto, id);
        mapToEntity(entity, dto);
        return pcmsoAnaliticoMapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("PCMSO Analítico não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PcmsoAnaliticoListDTO> findAll(Pageable pageable, String search) {
        String searchTerm = (search == null || search.isBlank()) ? "" : search;
        return repository.findBySearchTerm(searchTerm, pageable).map(pcmsoAnaliticoMapper::toListDto);
    }

    @Override
    @Transactional(readOnly = true)
    public PcmsoAnaliticoResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(pcmsoAnaliticoMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("PCMSO Analítico não encontrado com ID: " + id));
    }

    private void mapToEntity(PcmsoAnaliticoEntity entity, PcmsoAnaliticoRequestDTO dto) {
        if (dto.getEmpresaId() != null) {
            EmpresaEntity empresa = empresaRepository.findById(dto.getEmpresaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com ID: " + dto.getEmpresaId()));
            entity.setEmpresa(empresa);
            entity.setUnidadeOperacional(null);
        } else if (dto.getUnidadeOperacionalId() != null) {
            UnidadeOperacionalEntity unidade = unidadeRepository.findById(dto.getUnidadeOperacionalId())
                    .orElseThrow(() -> new ResourceNotFoundException("Unidade Operacional não encontrada com ID: " + dto.getUnidadeOperacionalId()));
            entity.setUnidadeOperacional(unidade);
            entity.setEmpresa(null);
        }

        PrestadorServicoEntity medico = prestadorRepository.findById(dto.getMedicoResponsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico Responsável não encontrado com ID: " + dto.getMedicoResponsavelId()));

        entity.setMedicoResponsavel(medico);
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFim(dto.getDataFim());
        entity.setDiscussaoResultados(dto.getDiscussaoResultados());
    }

    private void validatePcmso(PcmsoAnaliticoRequestDTO dto, Long currentId) {
        if (dto.getEmpresaId() == null && dto.getUnidadeOperacionalId() == null) {
            throw new BusinessException("É obrigatório fornecer o ID da Empresa ou da Unidade Operacional.");
        }
        if (dto.getEmpresaId() != null && dto.getUnidadeOperacionalId() != null) {
            throw new BusinessException("Forneça o ID da Empresa ou da Unidade Operacional, mas não ambos.");
        }
        if (dto.getDataFim().isBefore(dto.getDataInicio())) {
            throw new BusinessException("A Data de Fim não pode ser anterior à Data de Início.");
        }

        // Validação de Unicidade
        if (currentId == null) { // CREATE
            if (dto.getEmpresaId() != null && repository.existsByEmpresaId(dto.getEmpresaId())) {
                throw new BusinessException("Já existe um PCMSO Analítico para esta Empresa.");
            }
            if (dto.getUnidadeOperacionalId() != null && repository.existsByUnidadeOperacionalId(dto.getUnidadeOperacionalId())) {
                throw new BusinessException("Já existe um PCMSO Analítico para esta Unidade Operacional.");
            }
        } else { // UPDATE
            if (dto.getEmpresaId() != null && repository.existsByEmpresaIdAndIdNot(dto.getEmpresaId(), currentId)) {
                throw new BusinessException("Já existe um PCMSO Analítico para esta Empresa.");
            }
            if (dto.getUnidadeOperacionalId() != null && repository.existsByUnidadeOperacionalIdAndIdNot(dto.getUnidadeOperacionalId(), currentId)) {
                throw new BusinessException("Já existe um PCMSO Analítico para esta Unidade Operacional.");
            }
        }
    }
}