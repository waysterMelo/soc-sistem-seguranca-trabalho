package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Medicina.Aso.MotivoAfastamentoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.MotivoAfastamentoResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Aso.MotivoAfastamentoEntity;
import com.ocupacional.soc.Exceptions.BusinessException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Medicina.Aso.MotivoAfastamentoMapper;
import com.ocupacional.soc.Repositories.Medicina.Aso.MotivoAfastamentoRepository;
import com.ocupacional.soc.Services.Medicina.Aso.MotivoAfastamentoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MotivoAfastamentoServiceImpl implements MotivoAfastamentoService {

    private final MotivoAfastamentoRepository repository;
    private final MotivoAfastamentoMapper mapper;

    @Override
    @Transactional
    public Page<MotivoAfastamentoResponseDTO> findAll(Pageable pageable, String search) {
        String searchTerm = (search == null || search.isBlank()) ? "" : search;
        return repository.findByDescricaoContainingIgnoreCaseOrCodigoContainingIgnoreCase(searchTerm, searchTerm, pageable)
                .map(mapper::toResponseDto);
    }

    @Override
    @Transactional
    public MotivoAfastamentoResponseDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Motivo de Afastamento não encontrado com ID: " + id));
    }

    @Override
    @Transactional
    public MotivoAfastamentoResponseDTO create(MotivoAfastamentoRequestDTO dto) {
        repository.findByCodigo(dto.getCodigo()).ifPresent(existing -> {
            throw new BusinessException("Já existe um motivo de afastamento com o código: " + dto.getCodigo());
        });
        MotivoAfastamentoEntity entity = mapper.toEntity(dto);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional
    public MotivoAfastamentoResponseDTO update(Long id, MotivoAfastamentoRequestDTO dto) {
        MotivoAfastamentoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motivo de Afastamento não encontrado com ID: " + id));

        repository.findByCodigo(dto.getCodigo()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new BusinessException("O código '" + dto.getCodigo() + "' já está em uso por outro motivo de afastamento.");
            }
        });

        mapper.updateEntityFromDto(dto, entity);
        return mapper.toResponseDto(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Motivo de Afastamento não encontrado com ID: " + id);
        }
        // Adicionar aqui validação para impedir exclusão se o motivo estiver em uso por um Afastamento
        repository.deleteById(id);
    }
}