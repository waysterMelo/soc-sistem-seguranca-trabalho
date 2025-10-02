package com.ocupacional.soc.Dto.Medicina.Pcmso;

import lombok.Data;

@Data
public class PrestadorServicoPcmsoDto {
    private Long id;
    private String nome;
    private String sobrenome;
    private String nis;
    private String conselho;
    private String numeroInscricaoConselho;
    private String estadoConselho;
}