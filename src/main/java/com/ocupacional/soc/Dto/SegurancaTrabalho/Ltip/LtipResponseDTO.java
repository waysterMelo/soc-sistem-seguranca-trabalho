package com.ocupacional.soc.Dto.SegurancaTrabalho.Ltip;

import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class LtipResponseDTO {
    private Long id;
    private FuncaoResponseDTO funcao;
    private LocalDate dataLevantamento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;
    private PrestadorServicoResponseDTO responsavelTecnico;
    private Set<PrestadorServicoResponseDTO> demaisElaboradores;
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
    private Set<LtipNr16AnexoResponseDTO> atividadesPericulosasAnexos;
    private Set<AparelhoResponseDTO> aparelhos;
}