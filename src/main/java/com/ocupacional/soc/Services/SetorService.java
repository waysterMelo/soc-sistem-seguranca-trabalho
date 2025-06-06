package com.ocupacional.soc.Services;

import com.ocupacional.soc.Dto.SetorRequestDTO;
import com.ocupacional.soc.Dto.SetorResponseDTO;

import java.util.List;
import java.util.Optional;

public interface SetorService {
    SetorResponseDTO criar(SetorRequestDTO dto);
    Optional<SetorResponseDTO> buscarPorId(Long id);
    List<SetorResponseDTO> listarTodos();
    List<SetorResponseDTO> listarPorEmpresa(Long empresaId);
    SetorResponseDTO atualizar(Long id, SetorRequestDTO dto);
    void deletar(Long id);
    Optional<SetorResponseDTO> buscarPorNomeEEmpresa(String nome, Long empresaId);

}
