package com.ocupacional.soc.Dto.SegurancaTrabalho;

import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.EmpresaSimpleResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.ProfissionalResponsavelResponseDTO;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.LtcatSituacao;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class LtcatResponseDTO {
    private Long id;
    private Long unidadeOperacionalId;
    private String nomeUnidadeOperacional;
    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
    private Integer alertaValidadeDias;
    private LtcatSituacao situacao;
    private String comentariosInternos;
    private String condicoesPreliminares;
    private String conteudoCapa;
    private String laudoResponsabilidadeTecnica;
    private String laudoIntroducao;
    private String laudoObjetivos;
    private String laudoConsideracoesGerais;
    private String laudoCriteriosAvaliacao;
    private String recomendacoesTecnicas;
    private String conclusao;
    private String planejamentoAnual;
    private List<LtcatAgenteNocivoResponseDTO> agentesNocivos;
    private Set<ProfissionalResponsavelResponseDTO> profissionaisAmbientais;
    private Set<PrestadorServicoResponseDTO> prestadoresServico;
    private Set<AparelhoResponseDTO> aparelhos;
    private Set<BibliografiaResponseDTO> bibliografias;
    private Set<FuncaoResponseDTO> funcoes;
    private Set<EmpresaSimpleResponseDTO> empresasContratantes;
}