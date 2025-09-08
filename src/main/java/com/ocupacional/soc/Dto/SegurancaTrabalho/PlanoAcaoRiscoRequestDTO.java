package com.ocupacional.soc.Dto.SegurancaTrabalho;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PlanoAcaoRiscoRequestDTO {
    @NotNull
    private Long riscoId;
    private String acao;
    private String responsavel;
    private LocalDate prazo;
    private String status;
}
