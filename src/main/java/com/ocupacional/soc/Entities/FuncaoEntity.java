package com.ocupacional.soc.Entities;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoGfip;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Table(name = "funcoes")
@Data
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
    @Column(nullable = false, columnDefinition = "TEXT")
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

    @OneToMany(mappedBy = "funcao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ProfissionalRegistroAmbientalEntity> profissionaisResponsaveis = new ArrayList<>();

    public void addRiscoPGR(RiscoTrabalhistaPgrEntity risco) {
        riscosPGR.add(risco);
        risco.setFuncao(this);
    }

    public void removeRiscoPGR(RiscoTrabalhistaPgrEntity risco) {
        riscosPGR.remove(risco);
        risco.setFuncao(null);
    }

    public void addProfissionalResponsavel(ProfissionalRegistroAmbientalEntity profissional) {
        profissionaisResponsaveis.add(profissional);
        profissional.setFuncao(this);
    }

    public void removeProfissionalResponsavel(ProfissionalRegistroAmbientalEntity profissional) {
        profissionaisResponsaveis.remove(profissional);
        profissional.setFuncao(null);
    }


}
