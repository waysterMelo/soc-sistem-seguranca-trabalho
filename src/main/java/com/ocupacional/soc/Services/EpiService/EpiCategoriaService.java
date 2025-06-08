package com.ocupacional.soc.Services.EpiService;

import com.ocupacional.soc.Dto.EpiDto.EpiCategoriaDTO;
import java.util.List;

public interface EpiCategoriaService {
    List<EpiCategoriaDTO> findAll();
    EpiCategoriaDTO findById(Long id);
    EpiCategoriaDTO create(EpiCategoriaDTO dto);
    EpiCategoriaDTO update(Long id, EpiCategoriaDTO dto);
    void delete(Long id);
}