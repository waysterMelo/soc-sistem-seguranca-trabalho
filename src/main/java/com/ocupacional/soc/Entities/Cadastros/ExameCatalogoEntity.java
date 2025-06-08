package com.ocupacional.soc.Entities.Cadastros;

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
public class ExameCatalogoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_exame", unique = true, length = 20)
    private String codigoExame;

    @Column(name = "nome_exame", nullable = false)
    private String nomeExame;



}
