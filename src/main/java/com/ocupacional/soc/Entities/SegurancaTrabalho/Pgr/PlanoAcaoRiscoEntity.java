package com.ocupacional.soc.Entities.SegurancaTrabalho.Pgr;

import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pgr_plano_acao_risco")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanoAcaoRiscoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pgr_id", nullable = false)
    private PgrEntity pgr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risco_catalogo_id", nullable = false)
    private RiscoCatalogoEntity risco;

    @Column(name = "acao")
    private String acao;

    private String responsavel;
    private LocalDate prazo;
    private String status;
}