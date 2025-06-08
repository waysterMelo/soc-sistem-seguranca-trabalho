package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoGfip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private String descricaoFuncao; // Será mapeado de 'descricao' da entidade
    private TipoGfip tipoGfip;
    private String atividadesInsalubres;
    private String informacoesComplementaresRegistrosAmbientais;
    private List<RiscoTrabalhistaPgrResponseDTO> riscosPGR;
    private List<ProfissionalResponsavelResponseDTO> profissionaisResponsaveis;
    private List<FuncaoAgenteNocivoResponseDTO> agentesNocivosEsocial;
    private List<FuncaoExamePcmsoResponseDTO> examesPcmso;
}