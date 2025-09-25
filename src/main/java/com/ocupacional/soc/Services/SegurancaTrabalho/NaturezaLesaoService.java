package com.ocupacional.soc.Services.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;

import java.util.List;

public interface NaturezaLesaoService {

    CatalogoSimplesDTO findById(Long id);
    List<CatalogoSimplesDTO> findAll();
    List<CatalogoSimplesDTO> findByDescricao(String descricao);
}