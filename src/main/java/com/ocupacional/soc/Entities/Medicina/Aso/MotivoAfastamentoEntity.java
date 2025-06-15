package com.ocupacional.soc.Entities.Medicina.Aso;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "motivos_afastamento")
@Data
public class MotivoAfastamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String descricao;
}