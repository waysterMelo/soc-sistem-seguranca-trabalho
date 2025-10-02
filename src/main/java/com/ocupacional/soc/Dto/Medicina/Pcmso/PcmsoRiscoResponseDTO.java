package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoSimpleResponseDTO;
import lombok.Data;

@Data
public class PcmsoRiscoResponseDTO {
    private Long id;
    private RiscoCatalogoSimpleResponseDTO riscoCatalogo;
}
