package com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LtipListDTO {
    private Long id;
    private String funcaoNome;
    private String setorNome;
    private String empresaNome;
    private LocalDate dataLevantamento;
    private LocalDate proximaRevisao;
    private boolean atividadesNaoInsalubres;
    private String responsavelTecnicoNome;
}