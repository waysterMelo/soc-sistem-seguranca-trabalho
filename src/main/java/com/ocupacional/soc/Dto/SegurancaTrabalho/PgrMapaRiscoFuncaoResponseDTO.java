package com.ocupacional.soc.Dto.SegurancaTrabalho;

import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoSimpleResponseDTO;
import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
public class PgrMapaRiscoFuncaoResponseDTO {
    private Long id;
    private Long funcaoId;
    private String nomeFuncao;
    private Set<RiscoCatalogoSimpleResponseDTO> riscosDoCatalogo;
    
}