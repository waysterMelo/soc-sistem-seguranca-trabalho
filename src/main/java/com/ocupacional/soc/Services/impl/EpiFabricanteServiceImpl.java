package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.EpiDto.EpiFabricanteDTO;
import com.ocupacional.soc.Entities.Epi.EpiFabricanteEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.EpiMappers.EpiFabricanteMapper;
import com.ocupacional.soc.Repositories.Epi.EpiFabricanteRepository;
import com.ocupacional.soc.Services.EpiService.EpiFabricanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EpiFabricanteServiceImpl implements EpiFabricanteService {

    private final EpiFabricanteRepository repository;
    private final EpiFabricanteMapper epiFabricanteMapper;

    @Override
    public List<EpiFabricanteDTO> findAll() {
        return repository.findAll().stream().map(epiFabricanteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EpiFabricanteDTO findById(Long id) {
        return repository.findById(id).map(epiFabricanteMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Fabricante não encontrado com ID: " + id));
    }

    @Override
    public EpiFabricanteDTO create(EpiFabricanteDTO dto) {
        EpiFabricanteEntity entity = epiFabricanteMapper.toEntity(dto);
        return epiFabricanteMapper.toDto(repository.save(entity));
    }

    @Override
    public EpiFabricanteDTO update(Long id, EpiFabricanteDTO dto) {
        EpiFabricanteEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fabricante não encontrado com ID: " + id));
        entity.setNome(dto.getNome());
        return epiFabricanteMapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Fabricante não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }
}