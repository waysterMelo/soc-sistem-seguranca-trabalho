package com.ocupacional.soc.Entities.Medicina.Pcmso;

import com.ocupacional.soc.Entities.Cadastros.UnidadeOperacionalEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import com.ocupacional.soc.Enuns.Medicina.Pcmso.PcmsoStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pcmso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE pcmso SET excluido = true WHERE id = ?")
@Where(clause = "excluido = false")
public class PcmsoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_operacional_id", nullable = false, unique = true) // Alterado para OneToOne implícito
    private UnidadeOperacionalEntity unidadeOperacional;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PcmsoStatus status;

    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
    private String capaImagemUrl;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String capa;

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