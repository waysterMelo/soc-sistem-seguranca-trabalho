package com.ocupacional.soc.Entities.Medicina.AcuidadeVisual;

import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.DiagnosticoComparativoEnum;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.ReavaliacaoEnum;
import com.ocupacional.soc.Enuns.Medicina.AcuidadeVisual.TipoAvaliacaoAcuideEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "acuidade_visual")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE acuidade_visual SET excluido = true WHERE id = ?")
@Where(clause = "excluido = false")
public class AcuidadeVisualEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registro_profissional_id", nullable = false)
    private ProfissionalRegistrosEntity registroProfissional;

    @Column(nullable = false)
    private LocalDate dataAvaliacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAvaliacaoAcuideEnum tipoAvaliacao;

    @Column(nullable = false)
    private boolean usaLentes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_responsavel_id", nullable = false)
    private PrestadorServicoEntity medicoResponsavel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoExameFuncao tipoExame;

    // Resultados Olho Direito (O.D.)
    @Column(nullable = false)
    private Integer odP1;
    @Column(nullable = false)
    private Integer odP2;
    @Column(nullable = false)
    private Integer odPercentual;

    // Resultados Olho Esquerdo (O.E.)
    @Column(nullable = false)
    private Integer oeP1;
    @Column(nullable = false)
    private Integer oeP2;
    @Column(nullable = false)
    private Integer oePercentual;

    // Resultados Ambos Olhos (A.O.)
    @Column(nullable = false)
    private Integer aoP1;
    @Column(nullable = false)
    private Integer aoP2;
    @Column(nullable = false)
    private Integer aoPercentual;

    @Lob
    @Column(nullable = false)
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiagnosticoComparativoEnum diagnosticoComparativo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReavaliacaoEnum reavaliacao;

    @Column(nullable = false)
    private boolean encaminhamentoComAfastamento;
}
