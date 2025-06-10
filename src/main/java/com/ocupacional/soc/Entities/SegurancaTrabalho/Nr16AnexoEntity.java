package com.ocupacional.soc.Entities.SegurancaTrabalho;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nr16_anexos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nr16AnexoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "norma_regulamentadora")
    private Integer normaRegulamentadora;

    @Column(name = "anexos")
    private String anexos;
}