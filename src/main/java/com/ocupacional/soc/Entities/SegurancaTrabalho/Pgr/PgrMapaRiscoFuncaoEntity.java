package com.ocupacional.soc.Entities.SegurancaTrabalho.Pgr;

import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pgr_mapa_risco_funcao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PgrMapaRiscoFuncaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pgr_id", nullable = false)
    private PgrEntity pgr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pgr_mapa_funcao_riscos_catalogo",
            joinColumns = @JoinColumn(name = "pgr_mapa_risco_funcao_id"),
            inverseJoinColumns = @JoinColumn(name = "risco_catalogo_id")
    )
    private Set<RiscoCatalogoEntity> riscosDoCatalogo;

    @ElementCollection
    @CollectionTable(name = "pgr_mapa_riscos_personalizados", joinColumns = @JoinColumn(name = "pgr_mapa_risco_funcao_id"))
    @Column(name = "descricao_risco")
    private List<String> riscosPersonalizados;
}