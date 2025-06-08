package com.ocupacional.soc.Dto.Cadastros;

import com.ocupacional.soc.Enuns.CadastroEmpresas.GrauRisco;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoEmpresa;
import com.ocupacional.soc.Enuns.CadastroEmpresas.TipoMatrizFilial;
import lombok.Data;

import java.util.List;

@Data
public class EmpresaDto {


    private Long id;
    private TipoEmpresa tipoEmpresa;
    private String cpfOuCnpj;
    private String inscricaoEstadual;
    private StatusEmpresa status;
    private String razaoSocial;
    private String nomeFantasia;
    private String logomarcaUrl;
    private EnderecoDto endereco;
    private List<TelefoneDto> telefones;
    private String email;
    private GrauRisco grauRisco;
    private Long cnaePrincipalId;
    private List<Long> cnaesSecundariosIds;
    private TipoMatrizFilial tipoMatrizFilial;
    private Long empresaMatrizId;
    private Long medicoResponsavelPcmssoId;
    private String observacoes;
}
