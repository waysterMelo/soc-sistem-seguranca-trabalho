package com.ocupacional.soc.Mapper.Cadastros;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorResponsavelRequestDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoSimplificadoDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {
        RiscoTrabalhistaPgrMapper.class,
        ProfissionalResponsavelMapper.class,
        EmpresaMapper.class,
        SetorMapper.class,
        FuncaoAgenteNocivoMapper.class, // Novo
        FuncaoExamePcmsoMapper.class    // Novo
})
public interface FuncaoMapper {

    FuncaoMapper INSTANCE = Mappers.getMapper(FuncaoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "empresaId", target = "empresa", qualifiedByName = "longIdToEmpresaEntity") // Renomeado para clareza
    @Mapping(source = "setorId", target = "setor", qualifiedByName = "longIdToSetorEntity")       // Renomeado para clareza
    @Mapping(source = "cboId", target = "cbo", qualifiedByName = "longIdToCboEntity")             // Renomeado para clareza
    @Mapping(source = "descricaoFuncao", target = "descricao") // Mapeamento explícito
    @Mapping(target = "riscosPGR", ignore = true) // Processado no service
    @Mapping(target = "agentesNocivosEsocial", ignore = true) // Processado no service
    @Mapping(target = "examesPcmso", ignore = true)
    FuncaoEntity requestDtoToEntity(FuncaoRequestDTO dto);


    @Mapping(source = "empresa", target = "empresa")
    @Mapping(source = "setor", target = "setor")
    @Mapping(source = "cbo.id", target = "cboId")
    @Mapping(source = "cbo.codigoCbo", target = "codigoCbo")
    @Mapping(source = "cbo.nomeOcupacao", target = "nomeCbo")
    @Mapping(source = "descricao", target = "descricaoFuncao")
    @Mapping(source = "riscosPGR", target = "riscosPGR")
    @Mapping(source = "agentesNocivosEsocial", target = "agentesNocivosEsocial")
    @Mapping(source = "examesPcmso", target = "examesPcmso")
    FuncaoResponseDTO entityToResponseDTO(FuncaoEntity entity);

    @Named("longIdToEmpresaEntity") // Renomeado para clareza
    default EmpresaEntity longIdToEmpresaEntity(Long id) {
        if (id == null) return null;
        EmpresaEntity empresa = new EmpresaEntity();
        empresa.setId(id);
        return empresa;
    }

    @Named("longIdToSetorEntity") // Renomeado para clareza
    default SetorEntity longIdToSetorEntity(Long id) {
        if (id == null) return null;
        SetorEntity setor = new SetorEntity();
        setor.setId(id);
        return setor;
    }

    @Named("longIdToCboEntity") // Renomeado para clareza
    default CboEntity longIdToCboEntity(Long id) {
        if (id == null) return null;
        CboEntity cbo = new CboEntity();
        cbo.setId(id);
        return cbo;
    }

    @Named("mapPrestadorServicosFromIds")
    default List<PrestadorServicoEntity> mapPrestadorServicosFromIds(List<PrestadorResponsavelRequestDTO> prestadorDTOs) {
        if (prestadorDTOs == null || prestadorDTOs.isEmpty()) {
            return new ArrayList<>();
        }

        return prestadorDTOs.stream()
                .map(dto -> {
                    if (dto.getPrestadorServicoId() == null) return null;
                    PrestadorServicoEntity prestador = new PrestadorServicoEntity();
                    prestador.setId(dto.getPrestadorServicoId());
                    return prestador;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Named("prestadoresServicosToSimplificadosDTO")
    default List<PrestadorServicoSimplificadoDTO> prestadoresServicosToSimplificadosDTO(List<PrestadorServicoEntity> prestadores) {
        if (prestadores == null || prestadores.isEmpty()) {
            return new ArrayList<>();
        }

        return prestadores.stream()
                .map(this::prestadorServicoToSimplificadoDTO)
                .collect(Collectors.toList());
    }

    @Named("prestadorServicoToSimplificadoDTO")
    default PrestadorServicoSimplificadoDTO prestadorServicoToSimplificadoDTO(PrestadorServicoEntity prestador) {
        if (prestador == null) {
            return null;
        }
        return PrestadorServicoSimplificadoDTO.builder()
                .id(prestador.getId())
                .nome(prestador.getNome())
                .cpfCnpj(prestador.getCpf())
                .conselho(String.valueOf(prestador.getConselho()))
                .numeroConselho(prestador.getNumeroInscricaoConselho())
                .telefone(prestador.getTelefone1())
                .email(prestador.getEmail())
                .build();
    }

}