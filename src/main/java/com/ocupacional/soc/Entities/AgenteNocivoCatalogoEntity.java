package com.ocupacional.soc.Entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "agentes_nocivos_catalogo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgenteNocivoCatalogoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_esocial", nullable = false, unique = true)
    private String codigoEsocial;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

}
