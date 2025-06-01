package com.ocupacional.soc.Mapper;

import com.ocupacional.soc.Dto.FuncaoExamePcmsoRequestDTO;
import com.ocupacional.soc.Dto.FuncaoExamePcmsoResponseDTO;
import com.ocupacional.soc.Entities.ExameCatalogoEntity;
import com.ocupacional.soc.Entities.FuncaoExamePcmsoEntity;
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