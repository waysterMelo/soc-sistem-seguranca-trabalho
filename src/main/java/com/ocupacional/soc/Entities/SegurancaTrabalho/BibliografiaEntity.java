package com.ocupacional.soc.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bibliografias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BibliografiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String edicao;
    private String editora;
    private Integer anoPublicacao;
}