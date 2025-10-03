package com.ocupacional.soc.Services.Medicina.Espirometria;

import com.ocupacional.soc.Dto.Cadastros.ContextoProfissionalResponseDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoListDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EspirometriaAvaliacaoService {
    Page<EspirometriaAvaliacaoListDTO> findAll(Pageable pageable, Long empresaId, Long setorId, String search);
    EspirometriaAvaliacaoResponseDTO findById(Long id);
    ContextoProfissionalResponseDTO findContextoProfissional(Long funcionarioId);
    EspirometriaAvaliacaoResponseDTO create(EspirometriaAvaliacaoRequestDTO dto);
    EspirometriaAvaliacaoResponseDTO update(Long id, EspirometriaAvaliacaoRequestDTO dto);
    void delete(Long id);
}