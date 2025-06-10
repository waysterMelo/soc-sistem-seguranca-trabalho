package com.ocupacional.soc.Entities.Cat;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cat_cid")
@Data
public class CidEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String codigo;

    private String descricao;
}