package com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat;

import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.BibliografiaResponseDTO;
import com.ocupacional.soc.Enuns.SegurancaTrabalho.LtcatSituacao;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class LtcatResponseDTO {
    private Long id;
    private UnidadeOperacionalResponseDTO unidadeOperacional;
    private LocalDate dataDocumento;
    private LocalDate dataVencimento;
    private Integer alertaValidadeDias;
    private LtcatSituacao situacao;
    private String comentariosInternos;
    private String condicoesPreliminares;
    private String imagemCapa;
    private String laudoResponsabilidadeTecnica;
    private String laudoIntroducao;
    private String laudoObjetivos;
    private String laudoConsideracoesGerais;
    private String laudoCriteriosAvaliacao;
    private String recomendacoesTecnicas;
    private String conclusao;
    private List<LtcatAgenteNocivoResponseDTO> agentesNocivos;
    private Set<PrestadorServicoResponseDTO> prestadoresServico;
    private Set<AparelhoResponseDTO> aparelhos;
    private Set<BibliografiaResponseDTO> bibliografias;
    private Set<FuncaoResponseDTO> funcoes;
}