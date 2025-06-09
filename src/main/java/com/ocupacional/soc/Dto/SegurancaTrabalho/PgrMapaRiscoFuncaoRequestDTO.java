package com.ocupacional.soc.Dto.SegurancaTrabalho;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class PgrMapaRiscoFuncaoRequestDTO {
    @NotNull
    private Long funcaoId;
    private List<Long> riscoCatalogoIds;
    private List<String> riscosPersonalizados;
}