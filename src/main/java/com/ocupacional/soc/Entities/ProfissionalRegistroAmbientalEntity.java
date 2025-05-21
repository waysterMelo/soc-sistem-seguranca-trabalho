package com.ocupacional.soc.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "profissional_registro_ambiental")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalRegistroAmbientalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false) // Alterado de medico_id para funcionario_id
    private FuncionarioEntity funcionario;

}
