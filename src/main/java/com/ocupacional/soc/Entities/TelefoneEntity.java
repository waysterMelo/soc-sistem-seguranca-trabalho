package com.ocupacional.soc.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "telefones_funcionarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelefoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3)
    private String ddd;

    @Column(length = 10)
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private FuncionarioEntity funcionario;
}