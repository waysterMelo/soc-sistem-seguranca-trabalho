package com.ocupacional.soc.Entities.Cadastros;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ocupacional.soc.Enuns.CadastroEmpresas.GrauRisco;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoEmpresa;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoMatrizFilial;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor@ToString(onlyExplicitlyIncluded = true)
public class EmpresaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipoEmpresa; // FÍSICA ou JURÍDICA

    private String cpfOuCnpj;

    private String inscricaoEstadual;

    @Enumerated(EnumType.STRING)
    private StatusEmpresa status;

    @Column(nullable = false)
    private String razaoSocial;

    private String nomeFantasia;

    private String logomarcaUrl;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TelefoneEntity> telefones;

    private String email;

    @Enumerated(EnumType.STRING)
    private GrauRisco grauRisco;

    @ManyToOne
    private CnaeEntity cnaePrincipal;

    @ManyToMany
    private List<CnaeEntity> cnaesSecundarios;

    @Enumerated(EnumType.STRING)
    private TipoMatrizFilial tipoMatrizFilial;

    @ManyToOne
    private EmpresaEntity empresaMatriz;

    @ManyToOne
    private PrestadorServicoEntity medicoResponsavelPcmsso;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @OneToMany(mappedBy = "empresa")
    @JsonManagedReference
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private List<UnidadeOperacionalEntity> unidadesOperacionais;
}
