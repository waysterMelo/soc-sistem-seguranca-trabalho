package com.ocupacional.soc.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "funcionario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true) // Exemplo: CPF ou matrícula
    private String matricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;
}
