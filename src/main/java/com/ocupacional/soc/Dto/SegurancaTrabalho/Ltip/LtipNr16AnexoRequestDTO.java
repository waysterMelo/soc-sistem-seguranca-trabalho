package com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip;

import lombok.Data;

@Data
public class LtipNr16AnexoRequestDTO {
    private Long id; // ID do LtipNr16AnexoEntity (para updates)
    private Long anexoId;
    private String avaliacao;
}