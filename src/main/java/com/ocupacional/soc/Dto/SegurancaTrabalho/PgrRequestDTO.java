package com.ocupacional.soc.Dto.SegurancaTrabalho;

import com.ocupacional.soc.Enuns.SegurancaTrabalho.MetodologiaPlanoAcao;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.TipoPgr;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class PgrRequestDTO {

    @NotNull
    private TipoPgr tipo;

    @NotNull
    private Long unidadeOperacionalId;

    private String conteudoCapa;
    private String termoValidacao;
    private String documentoBase;
    private String responsavelEmpresa;
    private LocalDate dataDocumento;
    private LocalDate dataRevisao;
    private String termoCiencia;
    private String inventarioRiscos;
    private MetodologiaPlanoAcao planoAcaoMetodologia;
    private String planoAcaoOrientacoes;
    private String consideracoesFinais;

    @Valid
    private List<PgrMapaRiscoFuncaoRequestDTO> mapaRiscos;

    // Campos NR 31
    private String nr31TrabalhoComAnimais;
    private String nr31OcorrenciasClimaticas;
    private String nr31EsforcosExcessivos;
    private String nr31RegrasTransito;
    private String nr31DescarteResiduos;
    private String nr31ProximidadeRedesEletricas;

    // Campos NR 22
    private String nr22AtmosferasExplosivas;
    private String nr22DeficienciasOxigenio;
    private String nr22VentilacaoMecanica;
    private String nr22ProtecaoRespiratoria;
    private String nr22ProtecaoAuditiva;
    private String nr22TrabalhosSubaquaticos;
    private String nr22EstabilidadeMacicos;
    private String nr22NovasTecnologias;
}