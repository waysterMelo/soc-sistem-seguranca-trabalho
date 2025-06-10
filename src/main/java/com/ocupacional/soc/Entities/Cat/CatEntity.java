package com.ocupacional.soc.Entities.Cat;

import com.ocupacional.soc.Entities.Cadastros.EnderecoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.Cat.*;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "cat")
@Data
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cabeçalho
    @ManyToOne @JoinColumn(name = "acidentado_funcionario_id", nullable = false)
    private FuncionarioEntity acidentado;

    private String numeroReciboCat;

    @Enumerated(EnumType.STRING) private TipoCat tipoCat;
    @Enumerated(EnumType.STRING) private IniciativaCat iniciativaCat;
    private String numeroCatOrigem;

    // Aba Acidente/Doença
    private String unidadeAtendimentoCnes;
    private LocalDate dataAcidente;
    private LocalTime horaAcidente;
    private Integer horasTrabalhadas;
    @Enumerated(EnumType.STRING) private TipoAcidente tipoAcidente;
    private Boolean houveAfastamento;
    private LocalDate ultimoDiaTrabalhado;
    private String localAcidenteEspecificacao;
    @OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "local_acidente_endereco_id")
    private EnderecoEntity localAcidenteEndereco;

    @Enumerated(EnumType.STRING)
    private TipoLocalAcidente tipoLocalAcidente;

    @Enumerated(EnumType.STRING) private TipoInscricaoLocalAcidente tipoInscricaoLocalAcidente;
    private String inscricaoLocalAcidente;

    @ManyToMany @JoinTable(name = "cat_partes_corpo_atingidas")
    private Set<ParteCorpoAtingidaEntity> partesCorpoAtingidas;

    @ManyToOne @JoinColumn(name = "agente_causador_id")
    private AgenteCausadorEntity agenteCausador;

    @ManyToOne @JoinColumn(name = "natureza_lesao_id")
    private NaturezaLesaoEntity naturezaLesao;

    @ManyToOne @JoinColumn(name = "situacao_geradora_id")
    private SituacaoGeradoraEntity situacaoGeradora;

    private Boolean houveRegistroPolicial;
    private Boolean houveObito;
    private LocalDate dataObito;
    @Lob private String observacoes;

    // Aba Atendimento
    private LocalDate dataAtendimento;
    private LocalTime horaAtendimento;
    private Boolean houveInternacao;
    private Integer duracaoTratamentoDias;
    private Boolean provavelAfastamento;

    @Lob
    private String diagnosticoProvavel;

    @ManyToOne
    @JoinColumn(name = "cid_id")
    private CidEntity cid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atestado_medico_id")
    private PrestadorServicoEntity atestadoMedico;


}