package com.ocupacional.soc.Entities.Aparelho;

import com.ocupacional.soc.Enuns.Aparelho.StatusAparelho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "aparelhos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AparelhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    private String calibracao;

    @Column(nullable = false)
    private LocalDate dataCalibracao;

    private LocalDate validadeCalibracao;

    @Lob
    private String criteriosAvaliacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAparelho status;

    private String certificadoUrl;
}