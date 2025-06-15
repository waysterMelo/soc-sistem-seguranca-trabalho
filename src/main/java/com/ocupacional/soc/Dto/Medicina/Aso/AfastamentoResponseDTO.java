package com.ocupacional.soc.Dto.Medicina.Aso;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CidDTO;
import com.ocupacional.soc.Enuns.Medicina.Aso.OnusRemuneracao;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoAcidenteAfastamento;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoRetificacaoAso;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AfastamentoResponseDTO {
    private Long id;
    private RegistroProfissionalSimpleDTO registroProfissional;
    private PrestadorServicoResponseDTO emitente;
    private MotivoAfastamentoResponseDTO motivoAfastamento;
    private CidDTO cid;
    private TipoAcidenteAfastamento tipoAcidente;
    private String cnpj;
    private OnusRemuneracao onusRemuneracao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long totalDias; // Campo calculado
    private String observacao;
    private Boolean mesmaDoenca60Dias;
    private Boolean alterarStatusFuncionario;
    private Boolean exibirRgAtestado;
    private Boolean exibirCpfAtestado;
    private TipoRetificacaoAso tipoRetificacao;
    private String numeroReciboEsocial;
}