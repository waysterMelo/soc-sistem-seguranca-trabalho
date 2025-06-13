package com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico;

import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ordens_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE ordens_servico SET excluido = true WHERE id = ?")
@Where(clause = "excluido = false")
public class OrdemServicoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String revisao;

    @Column(nullable = false)
    private LocalDate dataElaboracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id", nullable = false)
    private FuncionarioEntity funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcao_id", nullable = false)
    private FuncaoEntity funcao;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descricaoFuncao; // Cópia da descrição da função no momento da criação

    @Lob
    @Column(columnDefinition = "TEXT")
    private String descricaoOrdemServico;

    private boolean exibirDescricaoSetor;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String informacoesPreventivas;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String recomendacoes;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ordem_servico_aparelhos",
            joinColumns = @JoinColumn(name = "ordem_servico_id"),
            inverseJoinColumns = @JoinColumn(name = "aparelho_id"))
    @Builder.Default
    private Set<AparelhoEntity> equipamentosAdicionais = new HashSet<>();
}