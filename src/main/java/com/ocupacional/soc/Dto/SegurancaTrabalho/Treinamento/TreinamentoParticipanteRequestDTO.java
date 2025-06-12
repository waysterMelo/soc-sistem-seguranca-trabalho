package com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TreinamentoParticipanteRequestDTO {
    @NotNull
    private Long funcionarioId;
    private boolean concluiu;
    private boolean fezTreinamentoAnterior;
}