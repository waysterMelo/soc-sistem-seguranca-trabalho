package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Medicina.Toxicologico.ExameToxicologicoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Toxicologico.ExameToxicologicoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.LaboratorioEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.Medicina.Toxicologico.ExameToxicologicoEntity;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.Toxicologico.ExameToxicologicoMapper;
import com.ocupacional.soc.Repositories.Cadastros.LaboratorioRepository;
import com.ocupacional.soc.Repositories.Cadastros.ProfissionalRegistrosRepository;
import com.ocupacional.soc.Repositories.Medicina.Toxicologico.ExameToxicologicoRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Services.Medicina.Toxicologico.ExameToxicologicoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExameToxicologicoServiceImpl implements ExameToxicologicoService {

    private final ExameToxicologicoRepository exameRepository;
    private final ProfissionalRegistrosRepository registroRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final PrestadorServicoRepository prestadorRepository;
    private final ExameToxicologicoMapper exameMapper;

    @Override
    @Transactional
    public Page<ExameToxicologicoResponseDTO> findAll(Pageable pageable, String searchTerm) {
        String query = (searchTerm == null || searchTerm.isBlank()) ? "" : searchTerm;
        return exameRepository.findAllBySearchTerm(query, pageable).map(exameMapper::toResponseDto);
    }

    @Override
    @Transactional
    public ExameToxicologicoResponseDTO findById(Long id) {
        return exameRepository.findById(id)
                .map(exameMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Exame Toxicológico não encontrado com ID: " + id));
    }

    @Override
    @Transactional
    public ExameToxicologicoResponseDTO create(ExameToxicologicoRequestDTO dto) {
        if (exameRepository.existsByCodigoExame(dto.getCodigoExame())) {
            throw new BusinessException("Já existe um exame com o código: " + dto.getCodigoExame());
        }
        ExameToxicologicoEntity entity = new ExameToxicologicoEntity();
        mapToEntity(entity, dto);
        ExameToxicologicoEntity savedEntity = exameRepository.save(entity);
        return exameMapper.toResponseDto(savedEntity);
    }

    @Override
    @Transactional
    public ExameToxicologicoResponseDTO update(Long id, ExameToxicologicoRequestDTO dto) {
        if (exameRepository.existsByCodigoExameAndIdNot(dto.getCodigoExame(), id)) {
            throw new BusinessException("O código de exame " + dto.getCodigoExame() + " já está em uso por outro registro.");
        }
        ExameToxicologicoEntity entity = exameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exame Toxicológico não encontrado com ID: " + id));
        mapToEntity(entity, dto);
        ExameToxicologicoEntity updatedEntity = exameRepository.save(entity);
        return exameMapper.toResponseDto(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!exameRepository.existsById(id)) {
            throw new ResourceNotFoundException("Exame Toxicológico não encontrado com ID: " + id);
        }
        exameRepository.deleteById(id);
    }

    private void mapToEntity(ExameToxicologicoEntity entity, ExameToxicologicoRequestDTO dto) {
        ProfissionalRegistrosEntity registro = registroRepository.findById(dto.getRegistroProfissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Registro Profissional não encontrado com ID: " + dto.getRegistroProfissionalId()));

        LaboratorioEntity laboratorio = laboratorioRepository.findById(dto.getLaboratorioId())
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado com ID: " + dto.getLaboratorioId()));

        PrestadorServicoEntity medico = prestadorRepository.findById(dto.getMedicoResponsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico Responsável não encontrado com ID: " + dto.getMedicoResponsavelId()));

        entity.setRegistroProfissional(registro);
        entity.setDataExame(dto.getDataExame());
        entity.setCodigoExame(dto.getCodigoExame());
        entity.setLaboratorio(laboratorio);
        entity.setMedicoResponsavel(medico);
        entity.setEstado(dto.getEstado());
        entity.setTipoRetificacao(dto.getTipoRetificacao());
        entity.setNumeroReciboEsocial(dto.getNumeroReciboEsocial());
    }
}