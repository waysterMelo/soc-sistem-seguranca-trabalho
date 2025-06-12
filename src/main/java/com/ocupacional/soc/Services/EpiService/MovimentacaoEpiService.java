package com.ocupacional.soc.Services.EpiService;

import com.ocupacional.soc.Dto.EpiDto.MovimentacaoEpiResponseDTO;
import com.ocupacional.soc.Dto.EpiDto.MovimentacaoEpiRequestDTO;

import java.util.List;

public interface MovimentacaoEpiService {
    List<MovimentacaoEpiResponseDTO> registrarRetirada(MovimentacaoEpiRequestDTO dto);
}
