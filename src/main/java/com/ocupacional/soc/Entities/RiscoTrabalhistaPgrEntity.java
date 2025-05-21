package com.ocupacional.soc.Entities;

import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "risco_trabalhista_pgr")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiscoTrabalhistaPgrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_risco", nullable = false)
    private GrupoRisco grupo;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;


}
