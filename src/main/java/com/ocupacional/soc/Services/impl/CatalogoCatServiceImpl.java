package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CatalogoCatMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogoCatServiceImpl {
    public <T> List<CatalogoSimplesDTO> findAll(JpaRepository<T, Long> repository) {
        return repository.findAll().stream()
                .map(CatalogoCatMapper::toDto)
                .collect(Collectors.toList());
    }
}