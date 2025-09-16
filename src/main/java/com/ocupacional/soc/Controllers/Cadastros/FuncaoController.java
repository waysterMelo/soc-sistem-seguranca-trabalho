package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import com.ocupacional.soc.Services.Cadastros.FuncaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcoes")
@RequiredArgsConstructor
public class FuncaoController {

    private final FuncaoService funcaoService;

    @PostMapping
    public ResponseEntity<FuncaoResponseDTO> criarFuncao(@Valid @RequestBody FuncaoRequestDTO requestDTO) {
        FuncaoResponseDTO responseDTO = funcaoService.criarFuncao(requestDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncaoResponseDTO> buscarPorId(@PathVariable Long id) {
        FuncaoResponseDTO responseDTO = funcaoService.buscarFuncaoPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<FuncaoResponseDTO>> listarFuncoes(Pageable pageable) {
        Page<FuncaoResponseDTO> funcoes = funcaoService.listarFuncoes(pageable);
        return ResponseEntity.ok(funcoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncaoResponseDTO> atualizarFuncao(@PathVariable Long id,
                                                             @Valid @RequestBody FuncaoRequestDTO requestDTO) {
        FuncaoResponseDTO responseDTO = funcaoService.atualizarFuncao(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncao(@PathVariable Long id) {
        funcaoService.deletarFuncao(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<FuncaoResponseDTO> inativarFuncao(@PathVariable Long id){
        FuncaoResponseDTO result = funcaoService.inativarFuncao(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/setor/{setorId}")
    public ResponseEntity<Page<FuncaoResponseDTO>> listarFuncaoPorSetor(
            @PathVariable Long setorId,
            @PageableDefault(size = 10, sort = "nome") Pageable pageable){

        Page<FuncaoResponseDTO> pageResponse =

                funcaoService.listarFuncaoPorSetor
                        (setorId,pageable);
        return ResponseEntity.ok(pageResponse);
    }



}
