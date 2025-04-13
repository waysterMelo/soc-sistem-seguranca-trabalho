package com.ocupacional.soc.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Enums relacionados
enum TipoEmpresa {
    FISICA, 
    JURIDICA
}

enum StatusEmpresa {
    ATIVO,
    INATIVO,
    SUSPENSO,
    BLOQUEADO
}

enum GrauRisco {
    NAO_INFORMADO,
    GRAU_1, // até 135 dias
    GRAU_2, // até 135 dias
    GRAU_3, // até 90 dias
    GRAU_4  // até 90 dias
}

enum TipoMatrizFilial {
    MATRIZ,
    FILIAL
}



@Entity
@Table(name = "cadastro_de_empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Informações Básicas
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEmpresa tipoEmpresa;
    
    @Column(length = 14, nullable = false, unique = true)
    private String cpfCnpj;
    
    @Column(length = 20)
    private String inscricaoEstadual;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEmpresa status;
    
    @Column(nullable = false)
    private String razaoSocial;
    
    private String nomeFantasia;
    
    private String logoUrl;
    
    // Endereço
    @Embedded
    private Endereco endereco;

      // Contato
    @ElementCollection
    @CollectionTable(name = "empresa_telefones", joinColumns = @JoinColumn(name = "empresa_id"))
    private List<Telefone> telefones = new ArrayList<>();
    
    @Column(length = 100)
    private String email;
    
    // Outras Informações
    @Enumerated(EnumType.STRING)
    private GrauRisco grauRisco;
    
    @ManyToOne
    @JoinColumn(name = "cnae_principal_id")
    private Cnae cnaePrincipal;
    
    @ManyToMany
    @JoinTable(
        name = "empresa_cnaes_secundarios",
        joinColumns = @JoinColumn(name = "empresa_id"),
        inverseJoinColumns = @JoinColumn(name = "cnae_id")
    )
    private List<Cnae> cnaesSecundarios = new ArrayList<>();
    
    @Enumerated(EnumType.STRING)
    private TipoMatrizFilial tipoMatrizFilial;
    
    @ManyToOne
    @JoinColumn(name = "empresa_matriz_id")
    private Empresa empresaMatriz;
    
    @ManyToOne
    @JoinColumn(name = "medico_responsavel_id")
    private Medico medicoResponsavel;
    
    @Column(columnDefinition = "TEXT")
    private String observacoes;
    
    // Auditoria
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    
    @Column
    private LocalDateTime dataAtualizacao;


    // Métodos auxiliares
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }


// Classes de suporte
@Embeddable
class Endereco {
    @Column(length = 9)
    private String cep;
    
    @Column(length = 100)
    private String cidade;
    
    @Column(length = 2)
    private String estado;
    
    @Column(length = 150)
    private String logradouro;
    
    @Column(length = 10)
    private String numero;
    
    @Column(length = 100)
    private String bairro;
    
    @Column(length = 100)
    private String complemento;
    
    @Column(length = 50)
    private String regiao;
}

@Embeddable
class Telefone {
    @Column(length = 3)
    private String ddd;
    
    @Column(length = 10)
    private String numero;
    
}


}
