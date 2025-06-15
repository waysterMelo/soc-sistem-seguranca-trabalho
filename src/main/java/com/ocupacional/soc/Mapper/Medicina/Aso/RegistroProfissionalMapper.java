package com.ocupacional.soc.Mapper.Medicina.Aso;


import com.ocupacional.soc.Dto.Medicina.Aso.RegistroProfissionalSimpleDTO;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RegistroProfissionalMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "funcionario.nome", target = "nomeFuncionario")
    @Mapping(source = "funcao.nome", target = "nomeFuncao")
    @Mapping(source = "funcao.empresa.razaoSocial", target = "nomeEmpresa")
    RegistroProfissionalSimpleDTO toSimpleDto(ProfissionalRegistrosEntity entity);
}