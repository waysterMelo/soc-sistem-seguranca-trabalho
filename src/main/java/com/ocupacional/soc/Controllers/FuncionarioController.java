package com.ocupacional.soc.Controllers;

import com.ocupacional.soc.Dto.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.FuncionarioResponseDTO;
import com.ocupacional.soc.Services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    // Injeção de dependência via construtor
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criarFuncionario(@Valid @RequestBody FuncionarioRequestDTO requestDTO) {
        FuncionarioResponseDTO responseDTO = funcionarioService.criarFuncionario(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarFuncionarioPorId(@PathVariable Long id) {
        FuncionarioResponseDTO responseDTO = funcionarioService.buscarFuncionarioPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<FuncionarioResponseDTO> buscarFuncionarioPorCpf(@PathVariable String cpf) {
        // É importante sanitizar ou validar o formato do CPF aqui ou no service
        // para evitar problemas de segurança ou queries malformadas,
        // embora a validação de formato já esteja no DTO para criação/atualização.
        FuncionarioResponseDTO responseDTO = funcionarioService.buscarFuncionarioPorCpf(cpf);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioResponseDTO>> listarFuncionarios(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable,
            @RequestParam(required = false) Long empresaId) {
        Page<FuncionarioResponseDTO> pageResponse = funcionarioService.listarFuncionarios(pageable, empresaId);
        return ResponseEntity.ok(pageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody FuncionarioRequestDTO requestDTO) {
        FuncionarioResponseDTO responseDTO = funcionarioService.atualizarFuncionario(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}