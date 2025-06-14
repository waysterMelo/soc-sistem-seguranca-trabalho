package com.ocupacional.soc.Entities.Cadastros;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "profissional_registro_ambiental")
@Data
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
    @JoinColumn(name = "funcionario_id", nullable = false) // Alterado de medico_id para funcionario_id
    private FuncionarioEntity funcionario;

    @Column(name = "data_entrada_cargo")
    private LocalDate dataEntradaCargo;

}
