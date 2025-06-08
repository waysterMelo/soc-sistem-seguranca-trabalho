package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoResponseDTO;
import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import com.ocupacional.soc.Services.Cadastros.RiscoCatalogoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/riscos-catalogo")
public class RiscoCatalogoController {

    private final RiscoCatalogoService riscoCatalogoService;

    public RiscoCatalogoController(RiscoCatalogoService riscoCatalogoService) {
        this.riscoCatalogoService = riscoCatalogoService;
    }

    @PostMapping
    public ResponseEntity<RiscoCatalogoResponseDTO> criarRisco(@Valid @RequestBody RiscoCatalogoRequestDTO requestDTO) {
        RiscoCatalogoResponseDTO responseDTO = riscoCatalogoService.criarRisco(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiscoCatalogoResponseDTO> buscarRiscoPorId(@PathVariable Long id) {
        RiscoCatalogoResponseDTO responseDTO = riscoCatalogoService.buscarRiscoPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<RiscoCatalogoResponseDTO>> listarRiscos(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) GrupoRisco grupo) {
        Page<RiscoCatalogoResponseDTO> pageResponse = riscoCatalogoService.listarRiscos(pageable, grupo);
        return ResponseEntity.ok(pageResponse);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<RiscoCatalogoResponseDTO>> listarTodosRiscos(
            @RequestParam(required = false) GrupoRisco grupo) {
        List<RiscoCatalogoResponseDTO> listResponse = riscoCatalogoService.listarTodosRiscos(grupo);
        return ResponseEntity.ok(listResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RiscoCatalogoResponseDTO> atualizarRisco(@PathVariable Long id, @Valid @RequestBody RiscoCatalogoRequestDTO requestDTO) {
        RiscoCatalogoResponseDTO responseDTO = riscoCatalogoService.atualizarRisco(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRisco(@PathVariable Long id) {
        riscoCatalogoService.deletarRisco(id);
        return ResponseEntity.noContent().build();
    }
}