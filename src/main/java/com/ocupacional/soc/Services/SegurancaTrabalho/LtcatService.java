package com.ocupacional.soc.Services.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LtcatService {
    LtcatResponseDTO createLtcat(LtcatRequestDTO dto);
    LtcatResponseDTO getLtcatById(Long id);
    Page<LtcatResponseDTO> getAllLtcats(Pageable pageable);
    LtcatResponseDTO updateLtcat(Long id, LtcatRequestDTO dto);
    void deleteLtcat(Long id);
}