package com.ocupacional.soc.Entities.Cat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "situacao_geradora")
@Data
public class SituacaoGeradoraEntity {
    @Id
    private Long id; // Usaremos os códigos oficiais como ID
    private String descricao;
}