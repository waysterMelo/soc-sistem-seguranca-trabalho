package com.ocupacional.soc.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exames_catalogo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamesPCMSO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Pode ser um código interno ou do eSocial, se aplicável
    @Column(name = "codigo_exame", unique = true)
    private String codigoExame;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricaoOrientacoes;
}
