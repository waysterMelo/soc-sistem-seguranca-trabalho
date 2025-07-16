package com.ocupacional.soc.Services.impl;

import com.ocupacional.soc.Dto.Cadastros.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Entities.Cadastros.EmpresaEntity;
import com.ocupacional.soc.Entities.Cadastros.FuncionarioEntity;
import com.ocupacional.soc.Exceptions.InvalidRequestException;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.FuncionarioMapper;
import com.ocupacional.soc.Repositories.Cadastros.EmpresaRepository;
import com.ocupacional.soc.Repositories.Cadastros.FuncionarioRepository;
import com.ocupacional.soc.Services.Cadastros.FuncionarioService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor // Cria construtor para todas as dependências final
public class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EmpresaRepository empresaRepository;
    private final FuncionarioMapper funcionarioMapper;

    @Override
    @Transactional
    public FuncionarioResponseDTO criarFuncionario(FuncionarioRequestDTO requestDTO) {
        funcionarioRepository.findByCpf(requestDTO.getCpf()).ifPresent(f -> {
            throw new InvalidRequestException("CPF já cadastrado: " + requestDTO.getCpf());
        });
        funcionarioRepository.findByMatricula(requestDTO.getMatricula()).ifPresent(f -> {
            throw new InvalidRequestException("Matrícula já cadastrada: " + requestDTO.getMatricula());
        });
        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isBlank()) {
            funcionarioRepository.findByEmail(requestDTO.getEmail()).ifPresent(f -> {
                throw new InvalidRequestException("E-mail já cadastrado: " + requestDTO.getEmail());
            });
        }

        FuncionarioEntity funcionarioEntity = funcionarioMapper.requestDtoToEntity(requestDTO); // O AfterMapping cuidará dos telefones

        EmpresaEntity empresa = empresaRepository.findById(requestDTO.getEmpresaId())
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com ID: " + requestDTO.getEmpresaId()));
        funcionarioEntity.setEmpresa(empresa);

        // A lógica de telefones é agora tratada pelo @AfterMapping no FuncionarioMapper para criação

        FuncionarioEntity savedFuncionario = funcionarioRepository.save(funcionarioEntity);
        return funcionarioMapper.entityToResponseDto(savedFuncionario);
    }

    @Override
    public FuncionarioResponseDTO buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionarioMapper::entityToResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + id));
    }

    @Override
    public FuncionarioResponseDTO buscarFuncionarioPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf)
                .map(funcionarioMapper::entityToResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com CPF: " + cpf));
    }

    @Override
    public Page<FuncionarioResponseDTO> listarFuncionarios(Pageable pageable, Long empresaId) {
        Page<FuncionarioEntity> pageFuncionarioEntity;
        if (empresaId != null) {
            // Valida se a empresa existe antes de tentar buscar os funcionários
            if (!empresaRepository.existsById(empresaId)) {
                throw new ResourceNotFoundException("Empresa não encontrada com ID: " + empresaId + " para listar funcionários.");
            }
            pageFuncionarioEntity = funcionarioRepository.findByEmpresaId(empresaId, pageable);
        } else {
            pageFuncionarioEntity = funcionarioRepository.findAll(pageable);
        }
        return pageFuncionarioEntity.map(funcionarioMapper::entityToResponseDto);
    }

    @Override
    @Transactional
    public FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioRequestDTO requestDTO) {
        FuncionarioEntity funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com ID: " + id));

        // Validar duplicações para campos únicos se eles foram alterados
        if (!funcionarioExistente.getCpf().equals(requestDTO.getCpf())) {
            funcionarioRepository.findByCpf(requestDTO.getCpf()).ifPresent(f -> {
                if (!f.getId().equals(id)) throw new InvalidRequestException("CPF já cadastrado para outro funcionário.");
            });
        }
        if (!funcionarioExistente.getMatricula().equals(requestDTO.getMatricula())) {
            funcionarioRepository.findByMatricula(requestDTO.getMatricula()).ifPresent(f -> {
                if (!f.getId().equals(id)) throw new InvalidRequestException("Matrícula já cadastrada para outro funcionário.");
            });
        }
        if (requestDTO.getEmail() != null && !requestDTO.getEmail().isBlank() &&
                (funcionarioExistente.getEmail() == null || !funcionarioExistente.getEmail().equals(requestDTO.getEmail()))) {
            funcionarioRepository.findByEmail(requestDTO.getEmail()).ifPresent(f -> {
                if (!f.getId().equals(id)) throw new InvalidRequestException("Email já cadastrado para outro funcionário.");
            });
        }

        // Aplicar atualizações dos campos simples do DTO para a entidade
        funcionarioMapper.updateEntityFromDto(requestDTO, funcionarioExistente);

        // Atualizar empresa se o ID da empresa mudou
        if (!funcionarioExistente.getEmpresa().getId().equals(requestDTO.getEmpresaId())) {
            EmpresaEntity novaEmpresa = empresaRepository.findById(requestDTO.getEmpresaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Nova empresa não encontrada com ID: " + requestDTO.getEmpresaId()));
            funcionarioExistente.setEmpresa(novaEmpresa);
        }

        FuncionarioEntity updatedFuncionario = funcionarioRepository.save(funcionarioExistente);
        return funcionarioMapper.entityToResponseDto(updatedFuncionario);
    }

    @Override
    @Transactional
    public void deletarFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Funcionário não encontrado com ID: " + id);
        }
        // Adicionar aqui verificações de dependências antes de deletar, se o funcionário
        // estiver vinculado a outras entidades importantes que impediriam a exclusão.
        funcionarioRepository.deleteById(id);
    }
}