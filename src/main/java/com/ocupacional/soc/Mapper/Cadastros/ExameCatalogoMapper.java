package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.ExameCatalogoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.ExameCatalogoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.ExameCatalogoSimpleResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.ExameCatalogoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExameCatalogoMapper {
    ExameCatalogoMapper INSTANCE = Mappers.getMapper(ExameCatalogoMapper.class);

    ExameCatalogoResponseDTO toResponseDto(ExameCatalogoEntity entity);

    List<ExameCatalogoResponseDTO> toResponseDtoList(List<ExameCatalogoEntity> entities);

    @Mapping(target = "id", ignore = true)
    ExameCatalogoEntity requestDtoToEntity(ExameCatalogoRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(ExameCatalogoRequestDTO dto, @MappingTarget ExameCatalogoEntity entity);


    @Named("toExameCatalogoSimpleResponseDTO")
    default ExameCatalogoSimpleResponseDTO entityToSimpleResponseDto(ExameCatalogoEntity entity){
        if (entity == null){
            return null;
        }
        ExameCatalogoSimpleResponseDTO dto = new ExameCatalogoSimpleResponseDTO();
        dto.setId(entity.getId());
        dto.setCodigoExame(entity.getCodigoExame());
        dto.setNomeExame(entity.getNomeExame());

        // Log para depuração (opcional, remova após o teste)
 System.out.println("ExameCatalogoMapper.entityToSimpleResponseDto - Entidade Nome: " + entity.getNomeExame() + ", DTO Nome: " + dto.getNomeExame());
       return dto;
    }

}
