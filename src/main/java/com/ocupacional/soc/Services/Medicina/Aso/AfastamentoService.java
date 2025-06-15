package com.ocupacional.soc.Services.Medicina.Aso;

import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AfastamentoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AfastamentoService {
    Page<AfastamentoListDTO> findAll(Pageable pageable, String search);
    AfastamentoResponseDTO findById(Long id);
    AfastamentoResponseDTO createAfastamento(AfastamentoRequestDTO dto);
    AfastamentoResponseDTO updateAfastamento(Long id, AfastamentoRequestDTO dto);
    void deleteAfastamento(Long id);
}