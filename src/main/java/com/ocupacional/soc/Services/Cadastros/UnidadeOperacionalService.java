package com.ocupacional.soc.Services.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.UnidadeOperacionalResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UnidadeOperacionalService {
    UnidadeOperacionalResponseDTO criar(Long empresaId, UnidadeOperacionalRequestDTO dto);
    UnidadeOperacionalResponseDTO atualizar(Long unidadeId, UnidadeOperacionalRequestDTO dto);
    Optional<UnidadeOperacionalResponseDTO> buscarPorId(Long unidadeId);
    List<UnidadeOperacionalResponseDTO> listarPorEmpresaId(Long empresaId);
    void deletar(Long unidadeId);
    Long calcularTotalSetores(Long unidadeId);
    Page<UnidadeOperacionalResponseDTO> listarTodos(Pageable pageable);
}
