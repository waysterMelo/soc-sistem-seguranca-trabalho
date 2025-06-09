package com.ocupacional.soc.Entities.SegurancaTrabalho;

import com.ocupacional.soc.Entities.Cadastros.AgenteNocivoCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ltcat_agentes_nocivos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LtcatAgenteNocivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ltcat_id", nullable = false)
    private LtcatEntity ltcat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agente_nocivo_catalogo_id", nullable = false)
    private AgenteNocivoCatalogoEntity agenteNocivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;
}