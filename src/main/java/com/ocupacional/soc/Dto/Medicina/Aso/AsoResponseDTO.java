package com.ocupacional.soc.Dto.Medicina.Aso;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoResponseDTO;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoExameFuncao;
import com.ocupacional.soc.Enuns.Medicina.Aso.ConclusaoAso;
import com.ocupacional.soc.Enuns.Medicina.Aso.TipoRetificacaoAso;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class AsoResponseDTO {
    private Long id;
    private RegistroProfissionalSimpleDTO registroProfissional;
    private TipoRetificacaoAso tipoRetificacao;
    private LocalDate dataAsoRetificado;
    private TipoExameFuncao tipoAso;
    private LocalDate dataEmissao;
    private PrestadorServicoResponseDTO medicoExaminador;
    private PrestadorServicoResponseDTO medicoResponsavelPcmso;
    private ConclusaoAso conclusaoAso;
    private String observacoes;
    private String conclusaoColaborador;
    private Set<RiscoCatalogoResponseDTO> riscos;
    private List<AsoExameResponseDTO> exames;
}
