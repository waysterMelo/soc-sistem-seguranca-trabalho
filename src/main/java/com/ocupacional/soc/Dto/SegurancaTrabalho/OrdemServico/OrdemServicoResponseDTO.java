package com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico;


import com.ocupacional.soc.Dto.Aparelhos.AparelhoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrdemServicoResponseDTO {
    private Long id;
    private String revisao;
    private LocalDate dataElaboracao;
    private FuncionarioResponseDTO funcionario;
    private FuncaoResponseDTO funcao;
    private String descricaoFuncao;
    private String descricaoOrdemServico;
    private boolean exibirDescricaoSetor;
    private String informacoesPreventivas;
    private String recomendacoes;
    private String observacoes;
    private Set<AparelhoResponseDTO> equipamentosAdicionais;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;
}