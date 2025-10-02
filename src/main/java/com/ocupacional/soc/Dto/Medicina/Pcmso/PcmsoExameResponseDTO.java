package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.ocupacional.soc.Enuns.Medicina.Pcmso.TipoExame;
import lombok.Data;

@Data
public class PcmsoExameResponseDTO {
    private Long id;
    private Long funcaoId;
    private String nomeFuncao;
    private Long exameId;
    private String nomeExame;
    private String codigoExame;
    private TipoExame tipoExame;
    private Integer periodicidadeMeses;
}
