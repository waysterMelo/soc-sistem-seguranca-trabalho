package com.ocupacional.soc.Entities.Cadastros;


import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroPrestador.TipoConselho;
import com.ocupacional.soc.Enuns.Funcionario.Sexo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prestadores_servico")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrestadorServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(unique = true, length = 14)
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 50)
    private String orgaoEmissorRg;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;

    private String telefone1;

    private String telefone2;

    @Column(length = 255)
    private String email;

    // --- Outras Informações ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cbo_id")
    private CboEntity cbo;

    @Column(length = 20)
    private String nis;

    @Enumerated(EnumType.STRING)
    private TipoConselho conselho;

    @Column(length = 50)
    private String numeroInscricaoConselho;

    @Column(length = 2)
    private String estadoConselho;

    @Enumerated(EnumType.STRING)
    private StatusEmpresa status;

}
