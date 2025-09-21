package com.ocupacional.soc.Entities.Cadastros;

import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoGfip;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "funcoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "setor_id", nullable = false)
    private SetorEntity setor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cbo_id")
    private CboEntity cbo;

    @Column(nullable = false)
    private String nome;

    private Integer quantidadeFuncionarios;

    @Lob
    @Column(name="descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_gfip", length = 100)
    private TipoGfip tipoGfip;

    @Column(name = "atividades_insalubres", columnDefinition = "TEXT")
    private String atividadesInsalubres;

    @Column(name = "informacoes_complementares_registros_ambientais", columnDefinition = "TEXT")
    private String informacoesComplementaresRegistrosAmbientais;

    @OneToMany(mappedBy = "funcao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<RiscoTrabalhistaPgrEntity> riscosPGR = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "funcao_prestador_servico",
            joinColumns = @JoinColumn(name = "funcao_id"),
            inverseJoinColumns = @JoinColumn(name = "prestador_servico_id")
    )
    @Builder.Default
    private List<PrestadorServicoEntity> prestadoresResponsaveis = new ArrayList<>();

    @OneToMany(mappedBy = "funcao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<FuncaoAgenteNocivoEntity> agentesNocivosEsocial = new ArrayList<>();

    @OneToMany(mappedBy = "funcao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<FuncaoExamePcmsoEntity> examesPcmso = new ArrayList<>();

    @OneToMany(mappedBy = "funcao", fetch = FetchType.LAZY)
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private StatusEmpresa status;

    public void addRiscoPGR(RiscoTrabalhistaPgrEntity risco) {
        riscosPGR.add(risco);
        risco.setFuncao(this);
    }

    public void removeRiscoPGR(RiscoTrabalhistaPgrEntity risco) {
        riscosPGR.remove(risco);
        risco.setFuncao(null);
    }

    public void addPrestadorResponsavel(PrestadorServicoEntity prestador) {
        if (prestadoresResponsaveis == null) {
            prestadoresResponsaveis = new ArrayList<>();
        }
        prestadoresResponsaveis.add(prestador);
    }

    public void removePrestadorResponsavel(PrestadorServicoEntity prestador) {
        if (prestadoresResponsaveis != null) {
            prestadoresResponsaveis.remove(prestador);
        }
    }

    public void addAgenteNocivoEsocial(FuncaoAgenteNocivoEntity agenteNocivo) {
        agentesNocivosEsocial.add(agenteNocivo);
        agenteNocivo.setFuncao(this);
    }

    public void removeAgenteNocivoEsocial(FuncaoAgenteNocivoEntity agenteNocivo) {
        agentesNocivosEsocial.remove(agenteNocivo);
        agenteNocivo.setFuncao(null);
    }

    public void addExamePcmso(FuncaoExamePcmsoEntity exame) {
        if (this.examesPcmso == null ){
            this.examesPcmso = new ArrayList<>();
        }
        this.examesPcmso.add(exame);
        exame.setFuncao(this);
    }

    public void removeExamePcmso(FuncaoExamePcmsoEntity exame) {
        if (this.examesPcmso != null){
            this.examesPcmso.remove(exame);
            exame.setFuncao(null);
        }
    }
}