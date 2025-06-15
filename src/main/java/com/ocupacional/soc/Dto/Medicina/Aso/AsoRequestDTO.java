package com.ocupacional.soc.Dto.Medicina.Aso;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.Aso.ConclusaoAso;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoRetificacaoAso;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AsoRequestDTO {
    @NotNull
    private Long registroProfissionalId;
    @NotNull
    private TipoRetificacaoAso tipoRetificacao;
    private LocalDate dataAsoRetificado;
    @NotNull
    private TipoExameFuncao tipoAso;
    @NotNull
    private LocalDate dataEmissao;
    @NotNull
    private Long medicoExaminadorId;
    @NotNull
    private Long medicoResponsavelPcmsoId;
    @NotNull
    private ConclusaoAso conclusaoAso;
    private String observacoes;
    private String conclusaoColaborador;

    private List<Long> riscoIds;
    private List<AsoExameRequestDTO> exames;
}