package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.ProfissionalResponsavelResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Entities.Cadastros.ProfissionalRegistrosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfissionalResponsavelMapper {

    ProfissionalResponsavelMapper INSTANCE = Mappers.getMapper(ProfissionalResponsavelMapper.class);

    @Mapping(source = "funcionario.id", target = "funcionarioId") // Alterado de medico.id
    @Mapping(source = "funcionario", target = "nomeFuncionario", qualifiedByName = "funcionarioToNomeFuncionario") // Alterado de medicoToNomeMedico
    ProfissionalResponsavelResponseDTO toResponseDTO(ProfissionalRegistrosEntity entity);

    @Named("funcionarioToNomeFuncionario")
    default String funcionarioToNomeFuncionario(FuncionarioEntity funcionario) { // Alterado de MedicoEntity
        return funcionario != null ? funcionario.getNome() : null; // Supondo que FuncionarioEntity tem o campo 'nome'
    }



}
