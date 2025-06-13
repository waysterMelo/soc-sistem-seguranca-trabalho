package com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrdemServicoRequestDTO {

    @NotNull(message = "A data de elaboração é obrigatória.")
    private LocalDate dataElaboracao;

    @NotNull(message = "O ID do funcionário é obrigatório.")
    private Long funcionarioId;

    @NotNull(message = "O ID da função é obrigatório.")
    private Long funcaoId;

    private String descricaoOrdemServico;

    private boolean exibirDescricaoSetor;

    private String informacoesPreventivas;

    private String recomendacoes;

    private String observacoes;

    private List<Long> equipamentosAdicionaisIds;
}