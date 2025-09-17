package com.ocupacional.soc.Dto.SegurancaTrabalho;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class LtipRequestDTO {
    private Long funcaoId;
    private LocalDate dataLevantamento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private Long responsavelTecnicoId;
    private List<Long> demaisElaboradoresIds;
    private String responsavelEmpresa;
    private LocalDate inicioValidade;
    private LocalDate proximaRevisao;
    private Integer alertaValidadeDias;
    private String imagemCapa;
    private String introducao;
    private String objetivo;
    private String definicoes;
    private String metodologia;
    private String descritivoAtividades;
    private String identificacaoLocal;
    private String conclusao;
    private String planejamentoAnual;
    private String avaliacaoAtividadesPericulosas;
    private boolean atividadesNaoInsalubres;
    private List<Long> atividadesPericulosasAnexosIds;
    private List<Long> bibliografiasIds;
    private List<Long> aparelhosIds;
}