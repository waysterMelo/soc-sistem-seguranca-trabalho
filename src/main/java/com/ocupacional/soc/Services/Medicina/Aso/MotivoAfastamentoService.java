package com.ocupacional.soc.Services.Medicina.Aso;

import com.ocupacional.soc.Dto.Medicina.Aso.MotivoAfastamentoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.MotivoAfastamentoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MotivoAfastamentoService {
    Page<MotivoAfastamentoResponseDTO> findAll(Pageable pageable, String search);
    MotivoAfastamentoResponseDTO findById(Long id);
    MotivoAfastamentoResponseDTO create(MotivoAfastamentoRequestDTO dto);
    MotivoAfastamentoResponseDTO update(Long id, MotivoAfastamentoRequestDTO dto);
    void delete(Long id);

}
