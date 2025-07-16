package com.ocupacional.soc.Entities.Cadastros;

import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "laboratorios")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE laboratorios SET excluido = true WHERE id = ?")
@Where(clause = "excluido = false")
public class LaboratorioEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String razaoSocial;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(unique = true, length = 18) // Formato com máscara: 00.000.000/0000-00
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private EnderecoEntity endereco;

    private String telefone;

    @Column(length = 255)
    private String email;
}