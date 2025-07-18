package com.ocupacional.soc.Entities.Epi;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "epi_fabricantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EpiFabricanteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

}
