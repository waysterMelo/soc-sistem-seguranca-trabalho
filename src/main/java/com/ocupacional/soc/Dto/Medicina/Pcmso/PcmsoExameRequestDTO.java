package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PcmsoExameRequestDTO {
    @NotNull(message = "O ID da Função é obrigatório.")
    private Long funcaoId;

    @NotNull(message = "O ID do Exame (do Catálogo) é obrigatório.")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Long> exameId;

    @NotNull(message = "O Tipo de Exame é obrigatório.")
    private TipoExameFuncao tipoExame;

    private Integer periodicidadeMeses;
}