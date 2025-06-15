package com.ocupacional.soc.Entities.Medicina.Fichaclinica;

import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fc_modelos")
@Getter
@Setter
public class FichaClinicaModeloEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private boolean imprimirDuasColunas;
    private boolean exibirCampoAssinatura;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FichaClinicaQuadroEntity> quadros = new ArrayList<>();
}