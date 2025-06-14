package com.ocupacional.soc.Dto.Medicina.Pcmso;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PcmsoListDTO {
    private Long id;
    private String nomeEmpresa;
    private String nomeUnidadeOperacional;
    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
}