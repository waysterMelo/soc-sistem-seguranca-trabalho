package com.ocupacional.soc.Mapper.Medicina.Espirometria;

import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoListDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Espirometria.EspirometriaAvaliacaoEntity;
import com.ocupacional.soc.Mapper.Cadastros.FuncionarioMapper;
import com.ocupacional.soc.Mapper.Mapper.AparelhoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FuncionarioMapper.class, AparelhoMapper.class})
public interface EspirometriaAvaliacaoMapper {

    @Mapping(target = "relacaoFev1Fvc", ignore = true) // Ignorado pois será calculado no serviço
    EspirometriaAvaliacaoResponseDTO toResponseDto(EspirometriaAvaliacaoEntity entity);

    @Mapping(source = "dataExame", target = "dataAvaliacao")
    @Mapping(source = "funcionario.empresa.nomeFantasia", target = "nomeEmpresa")
    @Mapping(source = "funcionario.nome", target = "nomeFuncionario")
    EspirometriaAvaliacaoListDTO toListDto(EspirometriaAvaliacaoEntity entity);
}