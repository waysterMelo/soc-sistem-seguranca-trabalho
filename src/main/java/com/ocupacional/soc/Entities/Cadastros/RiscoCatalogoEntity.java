package com.ocupacional.soc.Entities.Cadastros;

import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "riscos_catalogo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class
RiscoCatalogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_risco", nullable = false)
    private GrupoRisco grupo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

}
