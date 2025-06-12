package com.ocupacional.soc.Entities.Epi;

import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes_epi")
@Getter
@Setter
public class MovimentacaoEpiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private FuncionarioEntity funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "epi_epc_id", nullable = false)
    private EpiEpcEntity epi;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private LocalDateTime dataMovimentacao;

    @Lob
    @Column(nullable = false)
    private String termoCiencia;

}