package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Entities.Cat.NaturezaLesaoEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat.NaturezaLesaoRepository;
import com.ocupacional.soc.Services.SegurancaTrabalho.NaturezaLesaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NaturezaLesaoServiceImpl implements NaturezaLesaoService {

    private final NaturezaLesaoRepository repository;

    @Override
    public CatalogoSimplesDTO findById(Long id) {
        NaturezaLesaoEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Natureza da Lesão não encontrada"));
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

    private CatalogoSimplesDTO mapToDTO(NaturezaLesaoEntity entity) {
        CatalogoSimplesDTO dto = new CatalogoSimplesDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        return dto;
    }
}