package com.ocupacional.soc.Entities.SegurancaTrabalho.Pcmso;

import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pcmso_exames")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PcmsoExameEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pcmso_id", nullable = false)
    private PcmsoEntity pcmso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exame_catalogo_id", nullable = false)
    private ExameCatalogoEntity exame;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoExameFuncao tipoExame;

    private Integer periodicidadeMeses;
}