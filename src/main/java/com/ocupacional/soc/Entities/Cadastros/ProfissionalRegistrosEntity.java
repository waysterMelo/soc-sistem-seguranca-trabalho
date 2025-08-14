package com.ocupacional.soc.Entities.Cadastros;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profissional_registros")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalRegistrosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private FuncionarioEntity funcionario;

    @Column(name = "data_entrada_cargo")
    private LocalDate dataEntradaCargo;

}
