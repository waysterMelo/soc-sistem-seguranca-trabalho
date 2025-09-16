package com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat;

import com.ocupacional.soc.Enuns.SegurancaTrabalho.LtcatSituacao;
import lombok.Data;
import java.time.LocalDate;

@Data
public class LtcatListDTO {
    private Long id;
    private String nomeUnidadeOperacional;
    private String nomeEmpresa;
    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
    private LtcatSituacao situacao;
}
