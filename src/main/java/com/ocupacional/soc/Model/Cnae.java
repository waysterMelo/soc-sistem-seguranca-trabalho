package com.ocupacional.soc.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cnaes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cnae {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 10, nullable = false, unique = true)
    private String codigo;
    
    @Column(nullable = false)
    private String descricao;


}
