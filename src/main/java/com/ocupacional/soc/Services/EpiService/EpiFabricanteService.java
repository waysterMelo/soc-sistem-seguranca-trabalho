package com.ocupacional.soc.Services.EpiService;

import com.ocupacional.soc.Dto.EpiDto.EpiFabricanteDTO;
import java.util.List;

public interface EpiFabricanteService {
    List<EpiFabricanteDTO> findAll();
    EpiFabricanteDTO findById(Long id);
    EpiFabricanteDTO create(EpiFabricanteDTO dto);
    EpiFabricanteDTO update(Long id, EpiFabricanteDTO dto);
    void delete(Long id);
}