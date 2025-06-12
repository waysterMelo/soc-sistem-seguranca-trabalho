package com.ocupacional.soc.Entities.SegurancaTrabalho.Treinamento;

import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "treinamento_realizado")
@Getter
@Setter
public class TreinamentoRealizadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_catalogo_id", nullable = false)
    private TreinamentoCatalogoEntity treinamentoCatalogo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private EmpresaEntity empresa;

    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private LocalDate dataValidade;

    @Lob
    private String observacoes;

    @OneToMany(mappedBy = "treinamentoRealizado", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TreinamentoParticipante> participantes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "treinamento_responsaveis",
            joinColumns = @JoinColumn(name = "treinamento_realizado_id"),
            inverseJoinColumns = @JoinColumn(name = "prestador_servico_id"))
    private Set<PrestadorServicoEntity> responsaveis = new HashSet<>();
}