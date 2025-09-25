package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Entities.Cat.ParteCorpoAtingidaEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat.ParteCorpoAtingidaRepo;
import com.ocupacional.soc.Services.SegurancaTrabalho.ParteCorpoAtingidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParteCorpoAtingidaServiceImpl implements ParteCorpoAtingidaService {

    private final ParteCorpoAtingidaRepo repository;

    @Override
    public CatalogoSimplesDTO findById(Long id) {
        ParteCorpoAtingidaEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parte do Corpo Atingida não encontrada"));
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

    private CatalogoSimplesDTO mapToDTO(ParteCorpoAtingidaEntity entity) {
        CatalogoSimplesDTO dto = new CatalogoSimplesDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }
}