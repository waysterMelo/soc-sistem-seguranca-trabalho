package com.ocupacional.soc.Dto.Medicina.AcuidadeVisual;


import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.DiagnosticoComparativoEnum;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.ReavaliacaoEnum;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.TipoAvaliacaoAcuideEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AcuidadeVisualRequestDTO {

    @NotNull(message = "O registro profissional é obrigatório.")
    private Long registroProfissionalId;

    @NotNull(message = "A data da avaliação é obrigatória.")
    private LocalDate dataAvaliacao;

    @NotNull(message = "O tipo de avaliação é obrigatório.")
    private TipoAvaliacaoAcuideEnum tipoAvaliacao;

    @NotNull(message = "O campo 'Usa Lentes' é obrigatório.")
    private Boolean usaLentes;

    @NotNull(message = "O médico responsável é obrigatório.")
    private Long medicoResponsavelId;

    @NotNull(message = "O tipo de exame é obrigatório.")
    private TipoExameFuncao tipoExame;

    @NotNull(message = "Resultado O.D. (p1) é obrigatório.")
    private Integer odP1;
    @NotNull(message = "Resultado O.D. (p2) é obrigatório.")
    private Integer odP2;
    @NotNull(message = "Resultado O.D. (percentual) é obrigatório.")
    private Integer odPercentual;

    @NotNull(message = "Resultado O.E. (p1) é obrigatório.")
    private Integer oeP1;
    @NotNull(message = "Resultado O.E. (p2) é obrigatório.")
    private Integer oeP2;
    @NotNull(message = "Resultado O.E. (percentual) é obrigatório.")
    private Integer oePercentual;

    @NotNull(message = "Resultado A.O. (p1) é obrigatório.")
    private Integer aoP1;
    @NotNull(message = "Resultado A.O. (p2) é obrigatório.")
    private Integer aoP2;
    @NotNull(message = "Resultado A.O. (percentual) é obrigatório.")
    private Integer aoPercentual;

    @NotBlank(message = "O campo de observações é obrigatório.")
    private String observacoes;

    @NotNull(message = "O diagnóstico comparativo é obrigatório.")
    private DiagnosticoComparativoEnum diagnosticoComparativo;

    @NotNull(message = "A reavaliação é obrigatória.")
    private ReavaliacaoEnum reavaliacao;

    @NotNull(message = "O campo 'Encaminhamento com Afastamento' é obrigatório.")
    private Boolean encaminhamentoComAfastamento;
}