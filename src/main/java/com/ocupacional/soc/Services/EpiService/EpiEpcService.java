package com.ocupacional.soc.Services.EpiService;

import com.ocupacional.soc.Dto.EpiDto.EpiEpcRequestDTO;
import com.ocupacional.soc.Dto.EpiDto.EpiEpcResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EpiEpcService {
    Page<EpiEpcResponseDTO> findAll(Pageable pageable, Long empresaId, String search);
    EpiEpcResponseDTO findById(Long id);
    EpiEpcResponseDTO create(EpiEpcRequestDTO dto);
    EpiEpcResponseDTO update(Long id, EpiEpcRequestDTO dto);
    void delete(Long id);
}