package com.ocupacional.soc.Entities.SegurancaTrabalho;

import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.MetodologiaPlanoAcao;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.TipoPgr;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pgr")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PgrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_operacional_id", nullable = false)
    private UnidadeOperacionalEntity unidadeOperacional;

    private String conteudoCapa;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String termoValidacao;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String documentoBase;

    @Column(name = "responsavel_empresa")
    private String responsavelEmpresa;

    private LocalDate dataDocumento;
    private LocalDate dataRevisao;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String termoCiencia;

    @Enumerated(EnumType.STRING)
    private MetodologiaPlanoAcao planoAcaoMetodologia;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String planoAcaoOrientacoes;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String consideracoesFinais;

    @OneToMany(mappedBy = "pgr", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PgrMapaRiscoFuncaoEntity> mapaRiscos = new ArrayList<>();

    @OneToMany(mappedBy = "pgr", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PlanoAcaoRiscoEntity> planoAcaoRiscos = new ArrayList<>();

    public void addMapaRisco(PgrMapaRiscoFuncaoEntity mapaRisco) {
        mapaRiscos.add(mapaRisco);
        mapaRisco.setPgr(this);
    }

    public void addPlanoAcaoRisco(PlanoAcaoRiscoEntity planoAcaoRisco) {
        planoAcaoRiscos.add(planoAcaoRisco);
        planoAcaoRisco.setPgr(this);
    }
}