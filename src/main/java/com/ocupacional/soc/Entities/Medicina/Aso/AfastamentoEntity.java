package com.ocupacional.soc.Entities.Medicina.Aso;

import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import com.ocupacional.soc.Entities.Cat.CidEntity;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.AuditableEntity;
import com.ocupacional.soc.Enuns.Medicina.Aso.OnusRemuneracao;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoAcidenteAfastamento;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoRetificacaoAso;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "afastamentos")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE afastamentos SET excluido = true WHERE id = ?")
@Where(clause = "excluido = false")
public class AfastamentoEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registro_profissional_id", nullable = false)
    private ProfissionalRegistrosEntity registroProfissional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emitente_id", nullable = false)
    private PrestadorServicoEntity emitente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motivo_afastamento_id", nullable = false)
    private MotivoAfastamentoEntity motivoAfastamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid_id")
    private CidEntity cid;

    @Enumerated(EnumType.STRING)
    private TipoAcidenteAfastamento tipoAcidente;

    private String cnpj;

    @Enumerated(EnumType.STRING)
    private OnusRemuneracao onusRemuneracao;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataFim;

    @Lob
    private String observacao;

    private Boolean mesmaDoenca60Dias;
    private Boolean alterarStatusFuncionario;
    private Boolean exibirRgAtestado;
    private Boolean exibirCpfAtestado;

    @Enumerated(EnumType.STRING)
    private TipoRetificacaoAso tipoRetificacao;
    private String numeroReciboEsocial;
}