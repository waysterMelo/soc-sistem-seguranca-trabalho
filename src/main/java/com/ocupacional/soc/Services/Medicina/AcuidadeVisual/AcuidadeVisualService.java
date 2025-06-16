package com.ocupacional.soc.Services.Medicina.AcuidadeVisual;

import com.ocupacional.soc.Dto.Medicina.AcuidadeVisual.AcuidadeVisualRequestDTO;
import com.ocupacional.soc.Dto.Medicina.AcuidadeVisual.AcuidadeVisualResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AcuidadeVisualService {
    Page<AcuidadeVisualResponseDTO> findAll(Pageable pageable, String searchTerm);
    AcuidadeVisualResponseDTO findById(Long id);
    AcuidadeVisualResponseDTO create(AcuidadeVisualRequestDTO dto);
    AcuidadeVisualResponseDTO update(Long id, AcuidadeVisualRequestDTO dto);
    void delete(Long id);
}