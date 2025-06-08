package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoExamePcmsoResponseDTO {

    private Long id; // ID da entidade FuncaoExamePcmsoEntity
    private ExameCatalogoSimpleResponseDTO exameCatalogo;
    private TipoExameFuncao tipoExame;
    private Integer periodicidadeMeses;
    private boolean obrigatorio;


}
