package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncaoExamePcmsoResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncionarioResponseDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoTrabalhistaPgrResponseDTO;
import com.ocupacional.soc.Services.Cadastros.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

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

    @GetMapping("/setor/{setorId}")
    public ResponseEntity<Page<FuncionarioResponseDTO>> listarFuncionariosPorEmpresaSetor(
            @PathVariable Long setorId,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable){

        Page<FuncionarioResponseDTO> pageResponse =
                funcionarioService.listarFuncionariosPorEmpresaESetor
                        (setorId,pageable);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/{id}/exames")
    public ResponseEntity<List<FuncaoExamePcmsoResponseDTO>> obterExamesFuncionario(@PathVariable Long id) {
        List<FuncaoExamePcmsoResponseDTO> exames = funcionarioService.obterExamesPorFuncionario(id);
        return ResponseEntity.ok(exames);
    }

    @GetMapping("/{id}/riscos")
    public ResponseEntity<List<RiscoTrabalhistaPgrResponseDTO>> obterRiscosFuncionario(@PathVariable Long id) {
        List<RiscoTrabalhistaPgrResponseDTO> riscos = funcionarioService.obterRiscosPorFuncionario(id);
        return ResponseEntity.ok(riscos);
    }



}