package com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Enuns.SegurancaTrabalho.Treinamentos.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "treinamento_catalogo")
@Getter
@Setter
public class TreinamentoCatalogoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoTreinamento tipo;
    @Enumerated(EnumType.STRING)
    private TipoCapacitacao tipoCapacitacao;
    @Enumerated(EnumType.STRING)
    private ModalidadeTreinamento modalidade;
    private Integer duracaoHoras;
}