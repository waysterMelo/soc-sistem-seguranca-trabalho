package com.ocupacional.soc.Services.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncaoExamePcmsoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoTrabalhistaPgrResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FuncionarioService {
    FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO requestDTO);
    FuncionarioResponseDTO buscarFuncionarioPorId(Long id);
    FuncionarioResponseDTO buscarFuncionarioPorCpf(String cpf);
    Page<FuncionarioResponseDTO> listarFuncionarios(Pageable pageable, Long empresaId);
    FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioRequestDTO requestDTO);
    void deletarFuncionario(Long id);
    Page<FuncionarioResponseDTO> listarFuncionariosPorEmpresaESetor(Long setorId, Pageable pageable);
    List<FuncaoExamePcmsoResponseDTO> obterExamesPorFuncionario(Long funcionarioId);
    List<RiscoTrabalhistaPgrResponseDTO> obterRiscosPorFuncionario(Long funcionarioId);

}