package com.ocupacional.soc.Dto.EpiDto;

import com.ocupacional.soc.Enuns.CadastroEpi.TipoEpiEpc;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EpiEpcRequestDTO {

    @NotBlank(message = "O campo Nome é obrigatório")
    private String nome;

    private TipoEpiEpc tipo;

    private String modelo;

    private LocalDate validadeCA;

    private boolean apenasParaPGR;

    @NotBlank(message = "O campo Certificado de Avaliação do EPI é obrigatório")
    private String certificadoAvaliacao;

    private String periodicidadeUso;

    private Long categoriaId;

    private Long fabricanteId;

    @NotNull(message = "O ID da empresa é obrigatório.")
    private Long empresaId;

    private String criadoPor;
}