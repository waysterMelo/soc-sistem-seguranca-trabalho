package com.ocupacional.soc.Mapper.Medicina.PcmsoAnalitico;


import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoListDTO;
import com.ocupacional.soc.Dto.Medicina.PcmsoAnalitico.PcmsoAnaliticoResponseDTO;
import com.ocupacional.soc.Entities.Medicina.PcmsoAnalitico.PcmsoAnaliticoEntity;
import com.ocupacional.soc.Mapper.Cadastros.EmpresaMapper;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EmpresaMapper.class, UnidadeOperacionalMapper.class, PrestadorServicoMapper.class})
public interface PcmsoAnaliticoMapper {

    @Mapping(source = "empresa", target = "empresa")
    @Mapping(source = "unidadeOperacional", target = "unidadeOperacional")
    @Mapping(source = "medicoResponsavel", target = "medicoResponsavel")
    PcmsoAnaliticoResponseDTO toResponseDto(PcmsoAnaliticoEntity entity);

    @Mapping(source = "criadoEm", target = "dataCriacao")
    @Mapping(source = "empresa.nomeFantasia", target = "nomeEmpresa")
    @Mapping(source = "unidadeOperacional.nome", target = "nomeUnidadeOperacional")
    @Mapping(source = "medicoResponsavel.nome", target = "nomeResponsavel")
    PcmsoAnaliticoListDTO toListDto(PcmsoAnaliticoEntity entity);
}