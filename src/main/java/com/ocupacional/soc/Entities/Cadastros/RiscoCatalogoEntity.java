package com.ocupacional.soc.Entities.Cadastros;

import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco; // [upload:soc/Enuns/CadastroFuncoes/GrupoRisco.java]
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "riscos_catalogo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiscoCatalogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_risco", nullable = false)
    private GrupoRisco grupo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;


}
