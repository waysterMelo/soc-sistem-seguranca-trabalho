package com.ocupacional.soc.Entities.Medicina.Aso;


import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.Aso.ConclusaoAso;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoRetificacaoAso;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "aso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE aso SET excluido = true WHERE id = ?")
@Where(clause = "excluido = false")
public class AsoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registro_profissional_id", nullable = false)
    private ProfissionalRegistrosEntity registroProfissional;

    @Enumerated(EnumType.STRING)
    private TipoRetificacaoAso tipoRetificacao;

    private LocalDate dataAsoRetificado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoExameFuncao tipoAso;

    @Column(nullable = false)
    private LocalDate dataEmissao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_examinador_id", nullable = false)
    private PrestadorServicoEntity medicoExaminador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_responsavel_pcmso_id", nullable = false)
    private PrestadorServicoEntity medicoResponsavelPcmso;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConclusaoAso conclusaoAso;

    @Lob
    private String observacoes;

    @Lob
    private String conclusaoColaborador;

    // Relações com Riscos e Exames
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "aso_riscos",
            joinColumns = @JoinColumn(name = "aso_id"),
            inverseJoinColumns = @JoinColumn(name = "risco_catalogo_id"))
    @Builder.Default
    private Set<RiscoCatalogoEntity> riscos = new HashSet<>();

    @OneToMany(mappedBy = "aso", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AsoExameEntity> exames = new ArrayList<>();

    public void addExame(AsoExameEntity exame) {
        this.exames.add(exame);
        exame.setAso(this);
    }
}