package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoExamePcmsoRequestDTO {

    @NotNull(message = "O ID do Exame (Catálogo) é obrigatório.")
    private Long exameCatalogoId;

    @NotNull(message = "O tipo do exame é obrigatório.")
    private TipoExameFuncao tipoExame;

    private Integer periodicidadeMeses;
    private boolean obrigatorio = true;

}
