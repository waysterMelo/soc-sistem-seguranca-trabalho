package com.ocupacional.soc.Services.impl;


import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoCatalogoDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento.TreinamentoCatalogoEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Treinamento.TreinamentoCatalogoMapper;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Treinamento.TreinamentoCatalogoRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.Treinamento.TreinamentoCatalogoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreinamentoCatalogoServiceImpl implements TreinamentoCatalogoService {

    private final TreinamentoCatalogoRepository repository;
    private final TreinamentoCatalogoMapper treinamentoCatalogoMapper;

    @Override
    public List<TreinamentoCatalogoDTO> findAll() {
        return repository.findAll().stream().map(treinamentoCatalogoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TreinamentoCatalogoDTO create(TreinamentoCatalogoDTO dto) {
        TreinamentoCatalogoEntity entity = treinamentoCatalogoMapper.toEntity(dto);
        return treinamentoCatalogoMapper.toDto(repository.save(entity));
    }

    @Override
    public TreinamentoCatalogoDTO update(Long id, TreinamentoCatalogoDTO dto) {
        TreinamentoCatalogoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treinamento (Catálogo) não encontrado: " + id));
        treinamentoCatalogoMapper.updateEntityFromDto(dto, entity);
        return treinamentoCatalogoMapper.toDto(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Treinamento (Catálogo) não encontrado: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<TreinamentoCatalogoDTO> getById(Long id) {
        TreinamentoCatalogoEntity treinamentoCatalogoEntity =
                repository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Treinamento (Catálogo) não encontrado:" + id));
        return List.of(treinamentoCatalogoMapper.toDto(treinamentoCatalogoEntity));
    }
}