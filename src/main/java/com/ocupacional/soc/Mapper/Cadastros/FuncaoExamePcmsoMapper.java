package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncaoExamePcmsoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoExamePcmsoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncaoExamePcmsoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ExameCatalogoMapper.class}) // ExameCatalogoMapper já está em 'uses'
public interface FuncaoExamePcmsoMapper {
    FuncaoExamePcmsoMapper INSTANCE = Mappers.getMapper(FuncaoExamePcmsoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "funcao", ignore = true)
    @Mapping(source = "exameCatalogoId", target = "exameCatalogo", qualifiedByName = "idToExameCatalogoEntity")
    FuncaoExamePcmsoEntity requestDtoToEntity(FuncaoExamePcmsoRequestDTO dto);

    // ATUALIZE AQUI para usar o método nomeado do ExameCatalogoMapper
    @Mapping(source = "exameCatalogo", target = "exameCatalogo", qualifiedByName = "toExameCatalogoSimpleResponseDTO")
    FuncaoExamePcmsoResponseDTO entityToResponseDto(FuncaoExamePcmsoEntity entity);

    @Named("idToExameCatalogoEntity")
    default ExameCatalogoEntity idToExameCatalogoEntity(Long id) {
        if (id == null) {
            return null;
        }
        ExameCatalogoEntity exame = new ExameCatalogoEntity();
        exame.setId(id);
        return exame;
    }
}