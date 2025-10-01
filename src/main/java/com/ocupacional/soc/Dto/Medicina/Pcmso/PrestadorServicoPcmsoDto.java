package com.ocupacional.soc.Dto.Medicina.Pcmso;

import com.ocupacional.soc.Enuns.CadastroPrestador.TipoConselho;
import lombok.Data;

@Data
public class PrestadorServicoPcmsoDto {


    private Long id;
    private String nome;
    private String sobrenome;

    private String nis;
    private TipoConselho conselho;
    private String numeroInscricaoConselho;
    private String estadoConselho;
}
