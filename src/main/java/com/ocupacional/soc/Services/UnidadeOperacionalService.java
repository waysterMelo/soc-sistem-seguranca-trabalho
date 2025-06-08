package com.ocupacional.soc.Services;

import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UnidadeOperacionalService {
    UnidadeOperacionalResponseDTO criar(Long empresaId, UnidadeOperacionalRequestDTO dto);
    UnidadeOperacionalResponseDTO atualizar(Long unidadeId, UnidadeOperacionalRequestDTO dto);
    Optional<UnidadeOperacionalResponseDTO> buscarPorId(Long unidadeId);
    List<UnidadeOperacionalResponseDTO> listarPorEmpresaId(Long empresaId);
    void deletar(Long unidadeId);
}
