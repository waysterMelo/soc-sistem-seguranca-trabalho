package com.ocupacional.soc.Entities.Cadastros;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "risco_trabalhista_pgr") // Mantém o nome da tabela de associação
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiscoTrabalhistaPgrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "risco_catalogo_id", nullable = false)
    private RiscoCatalogoEntity riscoCatalogo;


}