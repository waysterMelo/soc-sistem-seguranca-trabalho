package com.ocupacional.soc.Dto.Medicina.Toxicologico;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.LaboratorioDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.RegistroProfissionalSimpleDTO;
import com.ocupacional.soc.Enuns.Medicina.TipoRetificacao;
import com.ocupacional.soc.Enuns.Medicina.Toxicologico.UfEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExameToxicologicoResponseDTO {
    private Long id;
    private RegistroProfissionalSimpleDTO registroProfissional;
    private LocalDate dataExame;
    private String codigoExame;
    private LaboratorioDTO laboratorio;
    private PrestadorServicoResponseDTO medicoResponsavel;
    private String crmMedicoResponsavel; // Campo derivado
    private UfEnum estado;
    private TipoRetificacao tipoRetificacao;
    private String numeroReciboEsocial;
}