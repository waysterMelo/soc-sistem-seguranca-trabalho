package com.ocupacional.soc.Services.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoCatalogoDTO;
import java.util.List;

public interface TreinamentoCatalogoService {
    TreinamentoCatalogoDTO create(TreinamentoCatalogoDTO dto);
    TreinamentoCatalogoDTO update(Long id, TreinamentoCatalogoDTO dto);
    List<TreinamentoCatalogoDTO> findAll();
    void delete(Long id);
    List<TreinamentoCatalogoDTO> getById(Long id);
}
