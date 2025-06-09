package com.ocupacional.soc.Services.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BibliografiaService {
    Page<BibliografiaResponseDTO> findAll(Pageable pageable, String search);
    BibliografiaResponseDTO findById(Long id);
    BibliografiaResponseDTO create(BibliografiaRequestDTO dto);
    BibliografiaResponseDTO update(Long id, BibliografiaRequestDTO dto);
    void delete(Long id);
}