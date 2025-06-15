package com.ocupacional.soc.Dto.Medicina.Aso;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AfastamentoListDTO {
    private Long id;
    private LocalDateTime dataCriacao;
    private String nomeEmpresa;
    private String nomeResponsavel; // Emitente
    private LocalDate dataInicio;
    private LocalDate dataFim;
}