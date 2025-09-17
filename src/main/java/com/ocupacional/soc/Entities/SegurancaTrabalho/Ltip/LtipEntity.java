package com.ocupacional.soc.Entities.SegurancaTrabalho.Ltip;

import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import com.ocupacional.soc.Entities.BibliografiaEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Nr16AnexoEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
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
    @Lob private String introducao;
    @Lob private String objetivo;
    @Lob private String definicoes;
    @Lob private String metodologia;
    @Lob private String descritivoAtividades;
    @Lob private String identificacaoLocal;
    @Lob private String conclusao;
    @Lob private String planejamentoAnual;
    @Lob private String avaliacaoAtividadesPericulosas;

    private boolean atividadesNaoInsalubres;

    @ManyToMany
    @JoinTable(name = "ltip_nr16_anexos",
            joinColumns = @JoinColumn(name = "ltip_id"),
            inverseJoinColumns = @JoinColumn(name = "anexo_id"))
    private Set<Nr16AnexoEntity> atividadesPericulosasAnexos;

    @ManyToMany
    @JoinTable(name = "ltip_bibliografias",
            joinColumns = @JoinColumn(name = "ltip_id"),
            inverseJoinColumns = @JoinColumn(name = "bibliografia_id"))
    private Set<BibliografiaEntity> bibliografias;

    @ManyToMany
    @JoinTable(name = "ltip_aparelhos",
            joinColumns = @JoinColumn(name = "ltip_id"),
            inverseJoinColumns = @JoinColumn(name = "aparelho_id"))
    private Set<AparelhoEntity> aparelhos;
}