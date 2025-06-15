package com.ocupacional.soc.Services.Medicina.Toxicologico;

import com.ocupacional.soc.Dto.Medicina.Toxicologico.ExameToxicologicoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Toxicologico.ExameToxicologicoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExameToxicologicoService {
    Page<ExameToxicologicoResponseDTO> findAll(Pageable pageable, String searchTerm);
    ExameToxicologicoResponseDTO findById(Long id);
    ExameToxicologicoResponseDTO create(ExameToxicologicoRequestDTO dto);
    ExameToxicologicoResponseDTO update(Long id, ExameToxicologicoRequestDTO dto);
    void delete(Long id);
}