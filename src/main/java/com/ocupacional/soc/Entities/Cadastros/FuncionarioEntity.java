package com.ocupacional.soc.Entities.Cadastros;

import com.ocupacional.soc.Enuns.Funcionario.Sexo;
import com.ocupacional.soc.Enuns.Funcionario.StatusFuncionario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 150)
    private String sobrenome;

    @Column(name = "cpf", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(name = "orgao_emissor_rg", length = 50)
    private String orgaoEmissorRg;

    @Column(name = "data_emissao_rg")
    private LocalDate dataEmissaoRg;

    @Column(name = "estado_emissor_rg", length = 2)
    private String estadoEmissorRg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Lob
    private StatusFuncionario status;

    @Column(length = 50)
    private String raca;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @Column(name = "estado_civil", length = 50)
    private String estadoCivil;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nome_mae", length = 250)
    private String nomeMae;

    @Column(name = "nome_pai", length = 250)
    private String nomePai;

    @Column(unique = true, length = 255)
    private String email;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(name = "criar_registro_profissional", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean criarRegistroProfissional;

    @Column(nullable = false, unique = true, length = 50)
    private String matricula;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;

    private String telefone1;

    private String telefone2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private EmpresaEntity empresa;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

    @Transient
    public Integer getIdade() {
        if (this.dataNascimento == null) {
            return null;
        }
        return java.time.Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}