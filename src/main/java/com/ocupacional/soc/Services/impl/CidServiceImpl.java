package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CidDTO;
import com.ocupacional.soc.Entities.Cat.CidEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CidMapper;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat.CidRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.CidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidServiceImpl implements CidService {

    private final CidRepository repository;
    private final CidMapper mapper;

    @Override
    public CidDTO findById(Long id) {
        CidEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CID não encontrado"));
        return mapper.toDto(entity);
    }

    @Override
    public List<CidDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CidDTO findByCodigo(String codigo) {
        CidEntity entity = repository.findByCodigo(codigo)
                .orElseThrow(() -> new ResourceNotFoundException("CID com código " + codigo + " não encontrado"));
        return mapper.toDto(entity);
    }

    @Override
    public List<CidDTO> findByDescricao(String descricao) {
        return repository.findByDescricaoContainingIgnoreCase(descricao).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<CidDTO> findByCodigo(String codigo, boolean parcial) {
        if (parcial) {
            return repository.findByCodigoContainingIgnoreCase(codigo).stream()
                    .map(mapper::toDto)
                    .toList();
        } else {
            CidEntity entity = repository.findByCodigo(codigo)
                    .orElseThrow(() -> new ResourceNotFoundException("CID com código " + codigo + " não encontrado"));
            return List.of(mapper.toDto(entity));
        }
    }

    @Override
    public List<CidDTO> search(String searchTerm) {
        return repository.findByCodigoOrDescricaoContainingIgnoreCase(searchTerm).stream()
                .map(mapper::toDto)
                .toList();
    }
}