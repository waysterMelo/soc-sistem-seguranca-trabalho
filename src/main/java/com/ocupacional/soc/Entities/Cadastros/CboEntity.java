package com.ocupacional.soc.Entities.Cadastros;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cbo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CboEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_cbo", nullable = false, unique = true, length = 7)
    private String codigoCbo;

    @Column(name = "nome_ocupacao", nullable = false, columnDefinition = "TEXT")
    private String nomeOcupacao;


}