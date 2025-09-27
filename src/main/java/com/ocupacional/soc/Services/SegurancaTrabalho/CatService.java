package com.ocupacional.soc.Services.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatResponseDTO;
import java.util.List;

public interface CatService {
    CatResponseDTO createCat(CatRequestDTO dto);
    CatResponseDTO updateCat(Long id, CatRequestDTO dto);
    void deleteCat(Long id);
    CatResponseDTO findById(Long id);
    List<CatResponseDTO> findAll();
    List<CatResponseDTO> findByFuncionarioId(Long funcionarioId);
}