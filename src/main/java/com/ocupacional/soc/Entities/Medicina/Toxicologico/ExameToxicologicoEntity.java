package com.ocupacional.soc.Entities.Medicina.Toxicologico;

import com.ocupacional.soc.Entities.Cadastros.LaboratorioEntity;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import com.ocupacional.soc.Enuns.Medicina.Toxicologico.TipoRetificacao;
import com.ocupacional.soc.Enuns.Medicina.Toxicologico.UfEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "exames_toxicologicos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE exames_toxicologicos SET excluido = true WHERE id = ?")
@Where(clause = "excluido = false")
public class ExameToxicologicoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registro_profissional_id", nullable = false)
    private ProfissionalRegistrosEntity registroProfissional;

    @Column(nullable = false)
    private LocalDate dataExame;

    @Column(unique = true, nullable = false)
    private String codigoExame;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "laboratorio_id", nullable = false)
    private LaboratorioEntity laboratorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_responsavel_id", nullable = false)
    private PrestadorServicoEntity medicoResponsavel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UfEnum estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRetificacao tipoRetificacao;

    private String numeroReciboEsocial;
}