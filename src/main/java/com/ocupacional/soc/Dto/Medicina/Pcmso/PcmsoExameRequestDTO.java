package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PcmsoExameRequestDTO {
    @NotNull(message = "O ID da Função é obrigatório.")
    private Long funcaoId;

    @NotNull(message = "O ID do Exame (do Catálogo) é obrigatório.")
    private List<Long> exameId;

    @NotNull(message = "O Tipo de Exame é obrigatório.")
    private TipoExameFuncao tipoExame;

    private Integer periodicidadeMeses;
}