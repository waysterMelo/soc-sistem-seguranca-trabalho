package com.ocupacional.soc.Dto.SegurancaTrabalho.Pcmso;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import lombok.Data;

@Data
public class PcmsoExameResponseDTO {
    private Long id;
    private Long funcaoId;
    private String nomeFuncao;
    private Long exameId;
    private String nomeExame;
    private String codigoExame;
    private TipoExameFuncao tipoExame;
    private Integer periodicidadeMeses;
}