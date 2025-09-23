package com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class LtipRequestDTO {
    private Long funcaoId;
    private LocalDate dataLevantamento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private Long responsavelTecnicoId;
    private Set<Long> demaisElaboradoresIds;
    private String responsavelEmpresa;
    private LocalDate inicioValidade;
    private LocalDate proximaRevisao;
    private Integer alertaValidadeDias;
    private String imagemCapa;
    private String introducao;
    private String objetivo;
    private String definicoes;
    private String descritivoAtividades;
    private String identificacaoLocal;
    private String conclusao;
    private boolean atividadesNaoInsalubres;
    private Set<LtipNr16AnexoRequestDTO> atividadesPericulosasAnexos;
    private Set<Long> aparelhosIds;
}