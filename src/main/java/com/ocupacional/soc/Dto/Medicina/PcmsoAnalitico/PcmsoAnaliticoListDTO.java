package com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PcmsoAnaliticoListDTO {
    private Long id;
    private LocalDateTime dataCriacao;
    private String nomeEmpresa;
    private String nomeUnidadeOperacional;
    private String nomeResponsavel;
    private LocalDate dataInicio;
    private LocalDate dataFim;
}