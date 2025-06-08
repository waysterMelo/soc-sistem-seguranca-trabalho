package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.FuncaoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.FuncaoResponseDTO;
import com.ocupacional.soc.Services.FuncaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/funcoes")
public class FuncaoController {

    private final FuncaoService funcaoService;

    @Autowired
    public FuncaoController(FuncaoService funcaoService) {
        this.funcaoService = funcaoService;
    }

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


}
