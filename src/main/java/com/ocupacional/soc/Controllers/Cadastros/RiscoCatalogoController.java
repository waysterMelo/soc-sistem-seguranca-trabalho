package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.RiscoCatalogoResponseDTO;
import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import com.ocupacional.soc.Services.Cadastros.RiscoCatalogoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
    @ResponseStatus(HttpStatus.CREATED)
    public RiscoCatalogoResponseDTO criarRisco(@Valid @RequestBody RiscoCatalogoRequestDTO requestDTO) {
        return riscoCatalogoService.criarRisco(requestDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiscoCatalogoResponseDTO> buscarRiscoPorId(@PathVariable Long id) {
        RiscoCatalogoResponseDTO responseDTO = riscoCatalogoService.buscarRiscoPorId(id);
        return ResponseEntity.ok(responseDTO);
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        riscoCatalogoService.deletarRisco(id);
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<Page<RiscoCatalogoResponseDTO>>
    listarRiscosDescricao(@RequestParam(required = false) String descricao,
                          @PageableDefault(size = 10) Pageable pageable){
        Page<RiscoCatalogoResponseDTO> lista =
                riscoCatalogoService.listarRiscosPorDescricao(descricao, pageable);
        return ResponseEntity.ok(lista);
    }

}