package com.ocupacional.soc.Entities.Cadastros;

import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoDocumento;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoEmpresa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "setores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SetorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = true)
    private EmpresaEntity empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_operacional_id", nullable = true)
    private UnidadeOperacionalEntity unidadeOperacional;

    @Enumerated(EnumType.STRING)
    private StatusEmpresa status;

    @Enumerated(EnumType.STRING)
    private TipoEmpresa tipoEmpresa;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    private Integer numeroDocumento;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FuncionarioEntity> funcionarios = new ArrayList<>();


}