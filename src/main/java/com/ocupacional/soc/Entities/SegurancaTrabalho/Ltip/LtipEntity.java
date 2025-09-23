package com.ocupacional.soc.Entities.SegurancaTrabalho.Ltip;

import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ltip")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LtipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    private LocalDate dataLevantamento;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_tecnico_id")
    private PrestadorServicoEntity responsavelTecnico;

    @ManyToMany
    @JoinTable(name = "ltip_demais_elaboradores",
            joinColumns = @JoinColumn(name = "ltip_id"),
            inverseJoinColumns = @JoinColumn(name = "prestador_servico_id"))
    private Set<PrestadorServicoEntity> demaisElaboradores;

    private String responsavelEmpresa;
    private LocalDate inicioValidade;
    private LocalDate proximaRevisao;
    private Integer alertaValidadeDias;

    @Lob
    private String imagemCapa;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String introducao;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String objetivo;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String definicoes;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descritivoAtividades;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String identificacaoLocal;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String conclusao;

    private boolean atividadesNaoInsalubres;

    @OneToMany(mappedBy = "ltip", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LtipNr16AnexoEntity> atividadesPericulosasAnexos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ltip_aparelhos",
            joinColumns = @JoinColumn(name = "ltip_id"),
            inverseJoinColumns = @JoinColumn(name = "aparelho_id"))
    private Set<AparelhoEntity> aparelhos;

}