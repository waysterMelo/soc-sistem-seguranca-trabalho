package com.ocupacional.soc.Services.Medicina.Pcmso;

import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PcmsoService {
    Page<PcmsoListDTO> findAll(Pageable pageable, String search);

    PcmsoResponseDTO findById(Long id);

    PcmsoResponseDTO create(PcmsoRequestDTO dto, MultipartFile imagemCapa);

    PcmsoResponseDTO update(Long id, PcmsoRequestDTO dto, MultipartFile imagemCapa);

    void delete(Long id);
}