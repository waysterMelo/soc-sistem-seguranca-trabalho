package com.ocupacional.soc.Services;

import com.ocupacional.soc.Dto.Cadastros.CboRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.CboResponseDTO;

import java.util.List;

public interface CboService {

    CboResponseDTO create(CboRequestDTO dto);
    CboResponseDTO findById(Long id);
    List<CboResponseDTO> findAll();
    CboResponseDTO update(Long id, CboRequestDTO dto);
    void delete(Long id);
}
