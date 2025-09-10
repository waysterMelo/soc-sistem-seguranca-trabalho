package com.ocupacional.soc.Entities.SegurancaTrabalho;

import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import com.ocupacional.soc.Entities.BibliografiaEntity;
import com.ocupacional.soc.Entities.Cadastros.*;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.LtcatSituacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ltcat")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LtcatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_operacional_id", nullable = false)
    private UnidadeOperacionalEntity unidadeOperacional;

    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
    private Integer alertaValidadeDias;

    @Enumerated(EnumType.STRING)
    private LtcatSituacao situacao;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String comentariosInternos;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String condicoesPreliminares;
    @Lob
    private String imagemCapa;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String laudoResponsabilidadeTecnica;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String laudoIntroducao;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String laudoObjetivos;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String laudoConsideracoesGerais;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String laudoCriteriosAvaliacao;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String recomendacoesTecnicas;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String conclusao;


    @OneToMany(mappedBy = "ltcat", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LtcatAgenteNocivoEntity> agentesNocivos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "ltcat_profissionais_ambientais",
            joinColumns = @JoinColumn(name = "ltcat_id"),
            inverseJoinColumns = @JoinColumn(name = "profissional_id"))
    @Builder.Default
    private Set<ProfissionalRegistrosEntity> profissionaisAmbientais = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ltcat_prestadores_servico",
            joinColumns = @JoinColumn(name = "ltcat_id"),
            inverseJoinColumns = @JoinColumn(name = "prestador_id"))
    @Builder.Default
    private Set<PrestadorServicoEntity> prestadoresServico = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ltcat_aparelhos",
            joinColumns = @JoinColumn(name = "ltcat_id"),
            inverseJoinColumns = @JoinColumn(name = "aparelho_id"))
    @Builder.Default
    private Set<AparelhoEntity> aparelhos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ltcat_bibliografias",
            joinColumns = @JoinColumn(name = "ltcat_id"),
            inverseJoinColumns = @JoinColumn(name = "bibliografia_id"))
    @Builder.Default
    private Set<BibliografiaEntity> bibliografias = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ltcat_funcoes",
            joinColumns = @JoinColumn(name = "ltcat_id"),
            inverseJoinColumns = @JoinColumn(name = "funcao_id"))
    @Builder.Default
    private Set<FuncaoEntity> funcoes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ltcat_empresas_contratantes",
            joinColumns = @JoinColumn(name = "ltcat_id"),
            inverseJoinColumns = @JoinColumn(name = "empresa_id"))
    @Builder.Default
    private Set<EmpresaEntity> empresasContratantes = new HashSet<>();
}