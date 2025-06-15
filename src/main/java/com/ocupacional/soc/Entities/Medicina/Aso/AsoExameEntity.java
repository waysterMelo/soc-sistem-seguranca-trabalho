package com.ocupacional.soc.Entities.Medicina.Aso;

import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aso_exames")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsoExameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aso_id", nullable = false)
    private AsoEntity aso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exame_catalogo_id", nullable = false)
    private ExameCatalogoEntity exame;


    @Column(name = "resultado_url")
    private String resultadoUrl;
}