package com.ocupacional.soc.Mapper.Medicina.Aso;


import com.ocupacional.soc.Dto.Medicina.Aso.AsoListDTO;
import com.ocupacional.soc.Dto.Medicina.Aso.AsoResponseDTO;
import com.ocupacional.soc.Entities.Medicina.Aso.AsoEntity;
import com.ocupacional.soc.Mapper.Cadastros.RiscoCatalogoMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        RegistroProfissionalMapper.class,
        PrestadorServicoMapper.class,
        RiscoCatalogoMapper.class,
        AsoExameMapper.class
})
public interface AsoMapper {

    @Mapping(source = "registroProfissional", target = "registroProfissional")
    @Mapping(source = "medicoExaminador", target = "medicoExaminador")
    @Mapping(source = "medicoResponsavelPcmso", target = "medicoResponsavelPcmso")
    @Mapping(source = "riscos", target = "riscos")
    @Mapping(source = "exames", target = "exames")
    AsoResponseDTO toResponseDto(AsoEntity entity);

    @Mapping(source = "registroProfissional.funcionario.nome", target = "nomeFuncionario")
    @Mapping(source = "registroProfissional.funcao.empresa.nomeFantasia", target = "nomeEmpresa")
    AsoListDTO toListDto(AsoEntity entity);

}