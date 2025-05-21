package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.ProfissionalResponsavelResponseDTO;
import com.ocupacional.soc.Entities.FuncionarioEntity;
import com.ocupacional.soc.Entities.ProfissionalRegistroAmbientalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfissionalResponsavelMapper {

    ProfissionalResponsavelMapper INSTANCE = Mappers.getMapper(ProfissionalResponsavelMapper.class);

    @Mapping(source = "funcionario.id", target = "funcionarioId") // Alterado de medico.id
    @Mapping(source = "funcionario", target = "nomeFuncionario", qualifiedByName = "funcionarioToNomeFuncionario") // Alterado de medicoToNomeMedico
    ProfissionalResponsavelResponseDTO toResponseDTO(ProfissionalRegistroAmbientalEntity entity);

    @Named("funcionarioToNomeFuncionario")
    default String funcionarioToNomeFuncionario(FuncionarioEntity funcionario) { // Alterado de MedicoEntity
        return funcionario != null ? funcionario.getNome() : null; // Supondo que FuncionarioEntity tem o campo 'nome'
    }



}
