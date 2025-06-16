package com.ocupacional.soc.Services.impl;


import com.ocupacional.soc.Dto.Medicina.AcuidadeVisual.AcuidadeVisualRequestDTO;
import com.ocupacional.soc.Dto.Medicina.AcuidadeVisual.AcuidadeVisualResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.Medicina.AcuidadeVisual.AcuidadeVisualEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.AcuidadeVisual.AcuidadeVisualMapper;
import com.ocupacional.soc.Repositories.Cadastros.ProfissionalRegistrosRepository;
import com.ocupacional.soc.Repositories.Medicina.AcuidadeVisual.AcuidadeVisualRepository;
import com.ocupacional.soc.Repositories.PrestadorServico.PrestadorServicoRepository;
import com.ocupacional.soc.Services.Medicina.AcuidadeVisual.AcuidadeVisualService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AcuidadeVisualServiceImpl implements AcuidadeVisualService {

    private final AcuidadeVisualRepository acuidadeVisualRepository;
    private final ProfissionalRegistrosRepository registroRepository;
    private final PrestadorServicoRepository prestadorRepository;
    private final AcuidadeVisualMapper acuidadeVisualMapper;

    @Override
    @Transactional
    public Page<AcuidadeVisualResponseDTO> findAll(Pageable pageable, String searchTerm) {
        String query = (searchTerm == null || searchTerm.isBlank()) ? "" : searchTerm;
        return acuidadeVisualRepository.findAllBySearchTerm(query, pageable)
                .map(acuidadeVisualMapper::toDto);
    }

    @Override
    @Transactional
    public AcuidadeVisualResponseDTO findById(Long id) {
        return acuidadeVisualRepository.findById(id)
                .map(acuidadeVisualMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Acuidade Visual não encontrado com ID: " + id));
    }

    @Override
    @Transactional
    public AcuidadeVisualResponseDTO create(AcuidadeVisualRequestDTO dto) {
        AcuidadeVisualEntity entity = new AcuidadeVisualEntity();
        mapToEntity(entity, dto);
        AcuidadeVisualEntity savedEntity = acuidadeVisualRepository.save(entity);
        return acuidadeVisualMapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public AcuidadeVisualResponseDTO update(Long id, AcuidadeVisualRequestDTO dto) {
        AcuidadeVisualEntity entity = acuidadeVisualRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Acuidade Visual não encontrado com ID: " + id));
        mapToEntity(entity, dto);
        AcuidadeVisualEntity updatedEntity = acuidadeVisualRepository.save(entity);
        return acuidadeVisualMapper.toDto(updatedEntity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!acuidadeVisualRepository.existsById(id)) {
            throw new ResourceNotFoundException("Registro de Acuidade Visual não encontrado com ID: " + id);
        }
        acuidadeVisualRepository.deleteById(id);
    }

    private void mapToEntity(AcuidadeVisualEntity entity, AcuidadeVisualRequestDTO dto) {
        ProfissionalRegistrosEntity registro = registroRepository.findById(dto.getRegistroProfissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Registro Profissional não encontrado com ID: " + dto.getRegistroProfissionalId()));

        PrestadorServicoEntity medico = prestadorRepository.findById(dto.getMedicoResponsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico Responsável não encontrado com ID: " + dto.getMedicoResponsavelId()));

        acuidadeVisualMapper.updateEntityFromDto(dto, entity);
        entity.setRegistroProfissional(registro);
        entity.setMedicoResponsavel(medico);
    }
}