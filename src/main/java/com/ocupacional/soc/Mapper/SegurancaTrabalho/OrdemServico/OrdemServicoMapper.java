package com.ocupacional.soc.Mapper.SegurancaTrabalho.OrdemServico;

import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoListDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoResponseDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.OrdemServico.OrdemServicoEntity;
import com.ocupacional.soc.Mapper.Cadastros.FuncaoMapper;
import com.ocupacional.soc.Mapper.Cadastros.FuncionarioMapper;
import com.ocupacional.soc.Mapper.Mapper.AparelhoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FuncionarioMapper.class, FuncaoMapper.class, AparelhoMapper.class})
public interface OrdemServicoMapper {

    @Mapping(source = "funcionario", target = "funcionario")
    @Mapping(source = "funcao", target = "funcao")
    @Mapping(source = "equipamentosAdicionais", target = "equipamentosAdicionais")
    OrdemServicoResponseDTO toResponseDto(OrdemServicoEntity entity);

    @Mapping(source = "funcionario.nome", target = "nomeFuncionario")
    @Mapping(source = "funcao.nome", target = "nomeFuncao")
    OrdemServicoListDTO toListDto(OrdemServicoEntity entity);
}