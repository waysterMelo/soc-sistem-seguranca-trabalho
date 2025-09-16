package com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat;

import lombok.Data;

@Data
public class LtcatAgenteNocivoResponseDTO {
    private Long id;
    private Long agenteNocivoId;
    private String agenteNocivoCodigo;
    private String agenteNocivoDescricao;
    private Long funcaoId;
    private String nomeFuncao;
}