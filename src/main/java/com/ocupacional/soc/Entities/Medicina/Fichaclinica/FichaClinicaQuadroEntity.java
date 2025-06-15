package com.ocupacional.soc.Entities.Medicina.Fichaclinica;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fc_quadros")
@Data
public class FichaClinicaQuadroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Lob
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelo_id", nullable = false)
    private FichaClinicaModeloEntity modelo;

    @OneToMany(mappedBy = "quadro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FichaClinicaPerguntaEntity> perguntas = new ArrayList<>();
}