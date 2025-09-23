package com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Nr16AnexoResponseDTO;
import lombok.Data;

@Data
public class LtipNr16AnexoResponseDTO {
    private Long id;
    private Nr16AnexoResponseDTO anexo;
    private String avaliacao;
}