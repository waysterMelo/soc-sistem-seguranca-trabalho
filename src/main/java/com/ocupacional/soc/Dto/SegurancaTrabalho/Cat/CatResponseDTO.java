package com.ocupacional.soc.Dto.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.Cat.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class CatResponseDTO {
    private Long id;
    private FuncionarioResponseDTO acidentado;
    private TipoCat tipoCat;
    private IniciativaCat iniciativaCat;
    private String numeroCatOrigem;
    private LocalDate dataAcidente;
    private LocalTime horaAcidente;
    private Integer horasTrabalhadas;
    private TipoAcidente tipoAcidente;
    private Boolean houveAfastamento;
    private LocalDate ultimoDiaTrabalhado;
    private String localAcidenteEspecificacao;
    private EnderecoDto localAcidenteEndereco;
    private TipoInscricaoLocalAcidente tipoInscricaoLocalAcidente;
    private String inscricaoLocalAcidente;
    private Set<CatalogoSimplesDTO> partesCorpoAtingidas;
    private CatalogoSimplesDTO agenteCausador;
    private CatalogoSimplesDTO naturezaLesao;
    private CatalogoSimplesDTO situacaoGeradora;
    private Boolean houveRegistroPolicial;
    private Boolean houveObito;
    private LocalDate dataObito;
    private String observacoes;
    private LocalDate dataAtendimento;
    private LocalTime horaAtendimento;
    private Boolean houveInternacao;
    private Integer duracaoTratamentoDias;
    private Boolean provavelAfastamento;
    private TipoLocalAcidente tipoLocalAcidente;
    private String numeroReciboCat;

    private String diagnosticoProvavel;
    private CidDTO cid;

    private PrestadorServicoResponseDTO atestadoMedico;
}