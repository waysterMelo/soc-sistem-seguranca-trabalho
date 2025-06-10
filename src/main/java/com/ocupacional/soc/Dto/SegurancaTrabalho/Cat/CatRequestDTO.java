package com.ocupacional.soc.Dto.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.Cat.*;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class CatRequestDTO {
    private String numeroReciboCat;
    @NotNull
    private Long acidentadoFuncionarioId;
    @NotNull
    private TipoCat tipoCat;
    @NotNull
    private IniciativaCat iniciativaCat;
    private String numeroCatOrigem;
    private String unidadeAtendimentoCnes;
    @NotNull
    private LocalDate dataAcidente;
    private LocalTime horaAcidente;
    private Integer horasTrabalhadas;
    private TipoAcidente tipoAcidente;
    private Boolean houveAfastamento;
    private LocalDate ultimoDiaTrabalhado;
    private String localAcidenteEspecificacao;
    private TipoInscricaoLocalAcidente tipoInscricaoLocalAcidente;
    private String inscricaoLocalAcidente;
    private EnderecoDto localAcidenteEndereco;
    private List<Long> partesCorpoAtingidasIds;
    private Long agenteCausadorId;
    private Long naturezaLesaoId;
    private Long situacaoGeradoraId;
    private Boolean houveRegistroPolicial;
    private Boolean houveObito;
    private LocalDate dataObito;
    @Lob
    private String observacoes;
    private LocalDate dataAtendimento;
    private LocalTime horaAtendimento;
    private Boolean houveInternacao;
    private Integer duracaoTratamentoDias;
    private Boolean provavelAfastamento;
    private String diagnosticoProvavel;
    private TipoLocalAcidente tipoLocalAcidente;
    private Long cidId;
    private Long atestadoMedicoId; // ID de um Medico ou Prestador
    private String tipoProfissionalAtestado; // "MEDICO" ou "PRESTADOR"
}