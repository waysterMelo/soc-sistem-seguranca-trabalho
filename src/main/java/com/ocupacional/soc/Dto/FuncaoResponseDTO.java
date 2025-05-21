package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoGfip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoResponseDTO {


    private Long id;
    private EmpresaSimpleResponseDTO empresa;
    private SetorResponseDTO setor;
    private Long cboId;
    private String nomeCbo;
    private String nome;
    private Integer quantidadeFuncionarios;
    private String descricaoFuncao;
    private TipoGfip tipoGfip;
    private String atividadesInsalubres;
    private String informacoesComplementaresRegistrosAmbientais;
    private List<RiscoTrabalhistaPgrResponseDTO> riscosPGR;
    private List<ProfissionalResponsavelResponseDTO> profissionaisResponsaveis;

}
