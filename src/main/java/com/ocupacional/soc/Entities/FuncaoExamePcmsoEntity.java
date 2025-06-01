package com.ocupacional.soc.Entities;


import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "funcao_exames_pcmso")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoExamePcmsoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exame_catalogo_id", nullable = false)
    private ExameCatalogoEntity exameCatalogo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_exame", nullable = false)
    private TipoExameFuncao tipoExame; // ADMISSIONAL, PERIODICO, etc.

    @Column(name = "periodicidade_meses")
    private Integer periodicidadeMeses; // Aplicável para exames periódicos

    @Column(name = "obrigatorio", nullable = false)
    private boolean obrigatorio = true;
}
