package com.ocupacional.soc.Services.Medicina.Aso;

import com.ocupacional.soc.Dto.Medicina.Aso.AsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AsoService {
    Page<AsoListDTO> findAll(Pageable pageable, String search);
    AsoResponseDTO findById(Long id);
    List<AsoListDTO> findByFuncionarioId(Long funcionarioId);
    AsoResponseDTO create(AsoRequestDTO dto, List<MultipartFile> files);
    AsoResponseDTO update(Long id, AsoRequestDTO dto);
    void delete(Long id);

}