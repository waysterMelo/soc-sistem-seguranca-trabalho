package com.ocupacional.soc.Entities.SegurancaTrabalho;

import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import com.ocupacional.soc.Entities.BibliografiaEntity;
import com.ocupacional.soc.Entities.Cadastros.*;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.LtcatSituacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ltcat")
@Data
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

    @Lob private String comentariosInternos;
    @Lob private String condicoesPreliminares;
    @Lob private String conteudoCapa;
    @Lob private String laudoResponsabilidadeTecnica;
    @Lob private String laudoIntroducao;
    @Lob private String laudoObjetivos;
    @Lob private String laudoConsideracoesGerais;
    @Lob private String laudoCriteriosAvaliacao;
    @Lob private String recomendacoesTecnicas;
    @Lob private String conclusao;
    @Lob private String planejamentoAnual;

    @OneToMany(mappedBy = "ltcat", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LtcatAgenteNocivoEntity> agentesNocivos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "ltcat_profissionais_ambientais",
            joinColumns = @JoinColumn(name = "ltcat_id"),
            inverseJoinColumns = @JoinColumn(name = "profissional_id"))
    @Builder.Default
    private Set<ProfissionalRegistroAmbientalEntity> profissionaisAmbientais = new HashSet<>();

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