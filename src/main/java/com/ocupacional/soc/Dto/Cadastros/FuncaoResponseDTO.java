package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoSimplificadoDTO;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoGfip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private String codigoCbo;
    private String nomeCbo;
    private String nome;
    private Integer quantidadeFuncionarios;
    private String descricaoFuncao;
    private TipoGfip tipoGfip;
    private String atividadesInsalubres;
    private String informacoesComplementaresRegistrosAmbientais;
    private List<RiscoTrabalhistaPgrResponseDTO> riscosPGR;
    private List<PrestadorServicoSimplificadoDTO> prestadoresResponsaveis = new ArrayList<>();
    private List<FuncaoAgenteNocivoResponseDTO> agentesNocivosEsocial;
    private List<FuncaoExamePcmsoResponseDTO> examesPcmso;
    private StatusEmpresa status;
}