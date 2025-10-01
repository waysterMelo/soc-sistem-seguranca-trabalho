package com.ocupacional.soc.Entities.Medicina.Pcmso;

import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import com.ocupacional.soc.Enuns.Medicina.Pcmso.PcmsoStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(
    name = "Pcmso.withPrestadoresAndCbo",
    attributeNodes = {
        @NamedAttributeNode(value = "medicoResponsavel", subgraph = "prestador-cbo"),
        @NamedAttributeNode(value = "elaboradores", subgraph = "prestador-cbo")
    },
    subgraphs = {
        @NamedSubgraph(
            name = "prestador-cbo",
            attributeNodes = {
                @NamedAttributeNode("cbo")
            }
        )
    }
)
@Entity
@Table(name = "pcmso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PcmsoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_operacional_id", nullable = false, unique = true)
    private UnidadeOperacionalEntity unidadeOperacional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_responsavel_id", nullable = false)
    private PrestadorServicoEntity medicoResponsavel;

    // Nova relação para Elaboradores
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pcmso_elaboradores",
            joinColumns = @JoinColumn(name = "pcmso_id"),
            inverseJoinColumns = @JoinColumn(name = "prestador_servico_id"))
    @Builder.Default
    private Set<PrestadorServicoEntity> elaboradores = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PcmsoStatus status;

    private LocalDate dataDocumento;
    private LocalDate dataVencimento;

    @Lob
    private String imagemCapa;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String introducao;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String sobrePcmso;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String conclusao;
    @OneToMany(mappedBy = "pcmso", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PcmsoExameEntity> exames = new ArrayList<>();

    // --- MÉTODOS HELPERS ---
    public void addExame(PcmsoExameEntity exame) {
        this.exames.add(exame);
        exame.setPcmso(this);
    }


}