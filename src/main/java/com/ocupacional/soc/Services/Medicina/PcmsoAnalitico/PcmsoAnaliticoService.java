package com.ocupacional.soc.Services.Medicina.PcmsoAnalitico;

import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoListDTO;
import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PcmsoAnaliticoService {

    Page<PcmsoAnaliticoListDTO> findAll(Pageable pageable, String search);

    PcmsoAnaliticoResponseDTO findById(Long id);

    PcmsoAnaliticoResponseDTO create(PcmsoAnaliticoRequestDTO dto);

    PcmsoAnaliticoResponseDTO update(Long id, PcmsoAnaliticoRequestDTO dto);

    void delete(Long id);
}
