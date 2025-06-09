package com.ocupacional.soc.Services.Aparelhos;

import com.ocupacional.soc.Dto.Aparelhos.AparelhoRequestDTO;
import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AparelhoService {
    Page<AparelhoResponseDTO> findAll(Pageable pageable, String search);
    AparelhoResponseDTO findById(Long id);
    AparelhoResponseDTO create(AparelhoRequestDTO dto, MultipartFile certificado);
    AparelhoResponseDTO update(Long id, AparelhoRequestDTO dto, MultipartFile certificado);
    void delete(Long id);
}