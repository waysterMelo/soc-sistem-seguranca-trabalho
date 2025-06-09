package com.ocupacional.soc.Entities.SegurancaTrabalho;

import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.MetodologiaPlanoAcao;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.TipoPgr;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pgr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PgrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPgr tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_operacional_id", nullable = false)
    private UnidadeOperacionalEntity unidadeOperacional;

    @Lob
    private String conteudoCapa;
    @Lob
    private String termoValidacao;
    @Lob
    private String documentoBase;
    @Column(name = "responsavel_empresa")
    private String responsavelEmpresa;
    private LocalDate dataDocumento;
    private LocalDate dataRevisao;
    @Lob
    private String termoCiencia;
    @Lob
    private String inventarioRiscos;
    @Enumerated(EnumType.STRING)
    private MetodologiaPlanoAcao planoAcaoMetodologia;
    @Lob
    private String planoAcaoOrientacoes;
    @Lob
    private String consideracoesFinais;

    @OneToMany(mappedBy = "pgr", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PgrMapaRiscoFuncaoEntity> mapaRiscos = new ArrayList<>();

    // --- Campos específicos para PGRTR (NR 31) ---
    @Lob
    private String nr31TrabalhoComAnimais;
    @Lob
    private String nr31OcorrenciasClimaticas;
    @Lob
    private String nr31EsforcosExcessivos;
    @Lob
    private String nr31RegrasTransito;
    @Lob
    private String nr31DescarteResiduos;
    @Lob
    private String nr31ProximidadeRedesEletricas;

    // --- Campos específicos para NR 22 ---
    @Lob
    private String nr22AtmosferasExplosivas;
    @Lob
    private String nr22DeficienciasOxigenio;
    @Lob
    private String nr22VentilacaoMecanica;
    @Lob
    private String nr22ProtecaoRespiratoria;
    @Lob
    private String nr22ProtecaoAuditiva;
    @Lob
    private String nr22TrabalhosSubaquaticos;
    @Lob
    private String nr22EstabilidadeMacicos;
    @Lob
    private String nr22NovasTecnologias;

    public void addMapaRisco(PgrMapaRiscoFuncaoEntity mapaRisco) {
        mapaRiscos.add(mapaRisco);
        mapaRisco.setPgr(this);
    }
}