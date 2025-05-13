package com.ocupacional.soc.Entities;


import com.ocupacional.soc.Enuns.GrauRisco;
import com.ocupacional.soc.Enuns.SituacaoUnidadeOperacional;
import com.ocupacional.soc.Enuns.TipoMatrizFilial;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "unidades_operacionais")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnidadeOperacionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoUnidadeOperacional situacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;

    private String endereco;

    @Column(name = "email_contato")
    private String emailContato;

    @Column(name = "ddd_telefone_1")
    private String dddTelefone1;

    @Column(name = "numero_telefone_1")
    private String numeroTelefone1;

    @Column(name = "ddd_telefone_2")
    private String dddTelefone2;

    @Column(name = "numero_telefone_2")
    private String numeroTelefone2;

    @Enumerated(EnumType.STRING)
    private GrauRisco grauRisco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cnae_principal_id")
    private CnaeEntity cnaePrincipal;


    @Column(nullable = false)
    private boolean alocadaEmEmpresaTerceira = false;

    @Enumerated(EnumType.STRING)
    private TipoMatrizFilial tipoConfiguracaoUnidade;

    private String cnpjEmpresaTerceira;

    private String razaoSocialEmpresaTerceira;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "unidade_operacional_setor",
            joinColumns = @JoinColumn(name = "unidade_operacional_id"),
            inverseJoinColumns = @JoinColumn(name = "setor_id")
    )
    private List<SetorEntity> setores;


}
