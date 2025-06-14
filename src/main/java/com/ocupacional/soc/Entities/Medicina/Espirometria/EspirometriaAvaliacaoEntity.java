package com.ocupacional.soc.Entities.Medicina.Espirometria;

import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.Espirometria.ConclusaoEspirometria;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "espirometria_avaliacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE espirometria_avaliacoes SET excluido = true WHERE id = ?")
@Where(clause = "excluido = false")
public class EspirometriaAvaliacaoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private FuncionarioEntity funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aparelho_id")
    private AparelhoEntity aparelhoUtilizado;

    @Column(nullable = false)
    private LocalDate dataExame;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoExameFuncao tipoExame;

    @Column(precision = 5, scale = 2)
    private BigDecimal altura;

    @Column(precision = 5, scale = 2)
    private BigDecimal peso;

    // Valores Encontrados
    @Column(precision = 10, scale = 4)
    private BigDecimal pef;

    @Column(precision = 10, scale = 4)
    private BigDecimal fev1;

    @Column(precision = 10, scale = 4)
    private BigDecimal fvc;

    // Conclusão
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ConclusaoEspirometria conclusao;

    @Lob
    private String outraConclusao; // Preenchido apenas se conclusao = OUTROS
}