package com.ocupacional.soc.Entities.Medicina.Fichaclinica;

import com.ocupacional.soc.Enuns.Medicina.Fichaclinica.TipoPergunta;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fc_perguntas")
@Data
public class FichaClinicaPerguntaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoPergunta tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quadro_id", nullable = false)
    private FichaClinicaQuadroEntity quadro;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FichaClinicaOpcaoPerguntaEntity> opcoes = new ArrayList<>();
}