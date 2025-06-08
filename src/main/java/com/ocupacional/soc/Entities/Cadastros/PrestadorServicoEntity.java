package com.ocupacional.soc.Entities.Cadastros;


import com.ocupacional.soc.Enuns.CadastroPrestador.TipoConselho;
import com.ocupacional.soc.Enuns.Funcionario.Sexo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prestadores_servico")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrestadorServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Informações Básicas ---
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(unique = true, length = 14) // Formato com máscara: XXX.XXX.XXX-XX
    private String cpf;

    @Column(length = 20)
    private String rg;

    @Column(length = 50)
    private String orgaoEmissorRg;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    // --- Endereço ---
    // Reutilizando a EnderecoEntity que já existe.
    // CascadeType.ALL significa que ao salvar um Prestador, o Endereço associado também será salvo.
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;

    // --- Informações de Contato ---
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "telefone1_id")
    private TelefoneEntity telefone1;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "telefone2_id")
    private TelefoneEntity telefone2;

    @Column(length = 255)
    private String email;

    // --- Outras Informações ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cbo_id") // Relação com a tabela de CBOs
    private CboEntity cbo;

    @Column(length = 20)
    private String nis; // PIS/PASEP/NIT

    @Enumerated(EnumType.STRING)
    private TipoConselho conselho;

    @Column(length = 50)
    private String numeroInscricaoConselho;

    @Column(length = 2) // Sigla do estado, ex: MG, SP
    private String estadoConselho;

}
