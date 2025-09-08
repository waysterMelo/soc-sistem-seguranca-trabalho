package com.ocupacional.soc.Dto.SegurancaTrabalho;


import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoResponseDTO;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PlanoAcaoRiscoResponseDTO {
    private Long id;
    private RiscoCatalogoResponseDTO risco;
    private String responsavel;
    private LocalDate prazo;
    private String status;
    private String acao;
}