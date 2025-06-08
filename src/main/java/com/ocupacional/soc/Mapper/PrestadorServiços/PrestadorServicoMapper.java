package com.ocupacional.soc.Mapper.PrestadorServiços;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoRequestDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.PrestadorServicoEntity;
import com.ocupacional.soc.Mapper.Cadastros.CboMapper;
import com.ocupacional.soc.Mapper.Cadastros.EnderecoMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {EnderecoMapper.class, CboMapper.class})
public interface PrestadorServicoMapper {

    // De DTO para Entity
    PrestadorServicoEntity toEntity(PrestadorServicoRequestDTO dto);

    // De Entity para DTO
    @Mapping(target = "cboId", source = "cbo.id")
    PrestadorServicoResponseDTO toDto(PrestadorServicoEntity entity);


    void updateEntityFromDto(PrestadorServicoRequestDTO dto, @MappingTarget PrestadorServicoEntity entity);


}
