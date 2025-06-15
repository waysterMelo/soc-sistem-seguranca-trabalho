package com.ocupacional.soc.Entities.Medicina.Fichaclinica;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fc_opcoes_pergunta")
@Data
public class FichaClinicaOpcaoPerguntaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String textoOpcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pergunta_id", nullable = false)
    private FichaClinicaPerguntaEntity pergunta;
}