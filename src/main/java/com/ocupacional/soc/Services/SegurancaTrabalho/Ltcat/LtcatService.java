package com.ocupacional.soc.Services.SegurancaTrabalho.Ltcat;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface LtcatService {
    LtcatResponseDTO createLtcat(LtcatRequestDTO dto, MultipartFile file);
    LtcatResponseDTO getLtcatById(Long id);
    Page<LtcatResponseDTO> getAllLtcats(Pageable pageable);
    LtcatResponseDTO updateLtcat(Long id, LtcatRequestDTO dto, MultipartFile imagemCapa);
    void deleteLtcat(Long id);
}