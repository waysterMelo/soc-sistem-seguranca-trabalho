package com.ocupacional.soc.Dto.SegurancaTrabalho;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LtcatAgenteNocivoRequestDTO {
    @NotNull
    private Long agenteNocivoId;
    @NotNull
    private Long funcaoId;
}