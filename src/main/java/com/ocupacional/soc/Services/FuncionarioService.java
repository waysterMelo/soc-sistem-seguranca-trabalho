package com.ocupacional.soc.Services;

import com.ocupacional.soc.Dto.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.FuncionarioResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FuncionarioService {
    FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO requestDTO);
    FuncionarioResponseDTO buscarFuncionarioPorId(Long id);
    FuncionarioResponseDTO buscarFuncionarioPorCpf(String cpf);
    Page<FuncionarioResponseDTO> listarFuncionarios(Pageable pageable, Long empresaId);
    FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioRequestDTO requestDTO);
    void deletarFuncionario(Long id);
}