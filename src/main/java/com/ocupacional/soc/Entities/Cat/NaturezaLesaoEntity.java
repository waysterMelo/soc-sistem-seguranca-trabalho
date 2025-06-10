package com.ocupacional.soc.Entities.Cat;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cat_natureza_lesao")
@Data
public class NaturezaLesaoEntity {
    @Id
    private Long id; // Usaremos os códigos oficiais como ID
    private String descricao;
}