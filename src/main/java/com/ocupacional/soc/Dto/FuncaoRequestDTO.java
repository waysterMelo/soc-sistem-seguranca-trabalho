package com.ocupacional.soc.Dto;

import com.ocupacional.soc.Enuns.CadastroFuncoes.TipoGfip;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FuncaoRequestDTO {

    @NotNull(message = "O ID da empresa não pode ser nulo.")
    private Long empresaId;

    @NotNull(message = "O ID do setor não pode ser nulo.")
    private Long setorId;

    @NotNull(message = "O ID do CBO não pode ser nulo.")
    private Long cboId;

    @NotBlank(message = "O nome da função não pode ser vazio.")
    @Size(max = 255, message = "O nome da função deve ter no máximo 255 caracteres.")
    private String nome;

    @PositiveOrZero(message = "A quantidade de funcionários deve ser um número positivo ou zero.")
    private Integer quantidadeFuncionarios;

    @NotBlank(message = "A descrição da função não pode ser vazia.")
    private String descricaoFuncao; // Será mapeado para 'descricao' na entidade

    private TipoGfip tipoGfip;

    private String atividadesInsalubres;

    private String informacoesComplementaresRegistrosAmbientais;

    @Valid
    private List<RiscoTrabalhistaPgrRequestDTO> riscosPGR;

    @Valid
    private List<ProfissionalResponsavelRequestDTO>profissionaisResponsaveis;

    @Valid
    private List<FuncaoAgenteNocivoRequestDTO> agentesNocivosEsocial;

    @Valid
    private List<FuncaoExamePcmsoRequestDTO> examesPcmso;
}