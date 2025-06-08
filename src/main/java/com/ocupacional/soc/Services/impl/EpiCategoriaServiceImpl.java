package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.EpiDto.EpiCategoriaDTO;
import com.ocupacional.soc.Entities.Epi.EpiCategoriaEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.EpiMappers.EpiCategoriaMapper;
import com.ocupacional.soc.Repositories.Epi.EpiCategoriaRepository;
import com.ocupacional.soc.Services.EpiService.EpiCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EpiCategoriaServiceImpl implements EpiCategoriaService {

    private final EpiCategoriaRepository repository;
    private final EpiCategoriaMapper epiCategoriaMapper;

    @Override
    public List<EpiCategoriaDTO> findAll() {
        return repository.findAll().stream().map(epiCategoriaMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public EpiCategoriaDTO findById(Long id) {
        return repository.findById(id).map(epiCategoriaMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
    }

    @Override
    public EpiCategoriaDTO create(EpiCategoriaDTO dto) {
        EpiCategoriaEntity entity = epiCategoriaMapper.toEntity(dto);
        return epiCategoriaMapper.toDto(repository.save(entity));
    }

    @Override
    public EpiCategoriaDTO update(Long id, EpiCategoriaDTO dto) {
        EpiCategoriaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
        entity.setNome(dto.getNome());
        return epiCategoriaMapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }
}