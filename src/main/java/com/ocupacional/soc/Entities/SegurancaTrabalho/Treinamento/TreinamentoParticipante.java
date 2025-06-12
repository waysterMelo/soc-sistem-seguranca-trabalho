package com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "treinamento_participante")
@Getter
@Setter
public class TreinamentoParticipante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_realizado_id")
    private TreinamentoRealizadoEntity treinamentoRealizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    private FuncionarioEntity funcionario;

    private boolean concluiu;
    private boolean fezTreinamentoAnterior;
}
