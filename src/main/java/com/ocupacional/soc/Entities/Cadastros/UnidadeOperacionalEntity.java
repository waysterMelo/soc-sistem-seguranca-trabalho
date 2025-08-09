package com.ocupacional.soc.Entities.Cadastros;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Enuns.CadastroEmpresas.GrauRisco;
import com.ocupacional.soc.Enuns.UnidadeOperacional.SituacaoUnidadeOperacional;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoMatrizFilial;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "unidades_operacionais")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
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
    @JsonBackReference
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private EmpresaEntity empresa;

    private String cep;
    private String cidade;
    private String estado;
    private String logradouro;
    private String numero;
    private String bairro;
    private String complemento;
    private String regiao;

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

    @OneToMany(mappedBy = "unidadeOperacional", fetch = FetchType.LAZY)
    private List<SetorEntity> setores;

}
