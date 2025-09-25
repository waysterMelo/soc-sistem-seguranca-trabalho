package com.ocupacional.soc.Services.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CidDTO;

import java.util.List;

public interface CidService {

    CidDTO findById(Long id);
    List<CidDTO> findAll();
    CidDTO findByCodigo(String codigo);
    List<CidDTO> findByDescricao(String descricao);
    List<CidDTO> findByCodigo(String codigo, boolean parcial);
    List<CidDTO> search(String searchTerm);
}