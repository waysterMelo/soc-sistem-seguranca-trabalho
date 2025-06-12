package com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TreinamentoRealizadoRequestDTO {
    @NotNull
    private Long empresaId;
    @NotNull
    private Long treinamentoCatalogoId;
    @NotNull
    private LocalDate dataInicio;
    @NotNull
    private LocalDate dataTermino;
    private LocalDate dataValidade;
    private String observacoes;
    private List<Long> responsaveisIds;
    @Valid
    private List<TreinamentoParticipanteRequestDTO> participantes;
}