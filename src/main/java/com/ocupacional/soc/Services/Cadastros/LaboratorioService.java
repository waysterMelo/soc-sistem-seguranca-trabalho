package com.ocupacional.soc.Services.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.LaboratorioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LaboratorioService {
    Page<LaboratorioDTO> findAll(Pageable pageable, String searchTerm);
    LaboratorioDTO findById(Long id);
    LaboratorioDTO create(LaboratorioDTO dto);
    LaboratorioDTO update(Long id, LaboratorioDTO dto);
    void delete(Long id);
}