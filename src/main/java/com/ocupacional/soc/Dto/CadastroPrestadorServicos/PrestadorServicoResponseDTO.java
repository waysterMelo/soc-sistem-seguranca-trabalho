package com.ocupacional.soc.Dto.CadastroPrestadorServicos;


import com.ocupacional.soc.Dto.Cadastros.EnderecoDto;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroPrestador.TipoConselho;
import com.ocupacional.soc.Enuns.Funcionario.Sexo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestadorServicoResponseDTO extends PrestadorServicoRequestDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String rg;
    private String orgaoEmissorRg;
    private Sexo sexo;
    private EnderecoDto endereco;
    private String email;
    private Long cboId;
    private String cboNomeOcupacao;
    private String nis;
    private TipoConselho conselho;
    private String numeroInscricaoConselho;
    private String estadoConselho;
    private StatusEmpresa status;
}
