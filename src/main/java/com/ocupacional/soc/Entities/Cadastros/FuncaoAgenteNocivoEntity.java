package com.ocupacional.soc.Entities.Cadastros;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcao_agentes_nocivos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoAgenteNocivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agente_nocivo_catalogo_id", nullable = false)
    private AgenteNocivoCatalogoEntity agenteNocivoCatalogo;
}
