package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Entities.Cat.AgenteCausadorEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat.AgenteCausadorRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.AgenteCausadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgenteCausadorServiceImpl implements AgenteCausadorService {

    private final AgenteCausadorRepository repository;

    @Override
    public CatalogoSimplesDTO findById(Long id) {
        AgenteCausadorEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agente Causador não encontrado"));
        return mapToDTO(entity);
    }

    @Override
    public List<CatalogoSimplesDTO> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<CatalogoSimplesDTO> findByDescricao(String descricao) {
        return repository.findByDescricaoContainingIgnoreCase(descricao).stream()
                .map(this::mapToDTO)
                .toList();
    }

    private CatalogoSimplesDTO mapToDTO(AgenteCausadorEntity entity) {
        CatalogoSimplesDTO dto = new CatalogoSimplesDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }
}