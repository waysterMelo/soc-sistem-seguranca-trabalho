package com.ocupacional.soc.Services.SegurancaTrabalho.Pcmso;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoListDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso.PcmsoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface PcmsoService {
    Page<PcmsoListDTO> findAll(Pageable pageable, String search);

    PcmsoResponseDTO findById(Long id);

    PcmsoResponseDTO create(PcmsoRequestDTO dto, MultipartFile capaImagem);

    PcmsoResponseDTO update(Long id, PcmsoRequestDTO dto, MultipartFile capaImagem);

    void delete(Long id);
}