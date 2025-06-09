package com.ocupacional.soc.Services.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.LtipResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LtipService {
    LtipResponseDTO createLtip(LtipRequestDTO dto);
    LtipResponseDTO updateLtip(Long id, LtipRequestDTO dto);
    LtipResponseDTO findLtipById(Long id);
    Page<LtipResponseDTO> findAllLtips(Pageable pageable, Long empresaId, Long setorId, Long funcaoId);
    void deleteLtip(Long id);
}