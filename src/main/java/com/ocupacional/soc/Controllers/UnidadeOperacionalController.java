package com.ocupacional.soc.Controllers;

import com.ocupacional.soc.Dto.UnidadeOperacionalRequestDTO;
import com.ocupacional.soc.Dto.UnidadeOperacionalResponseDTO;
import com.ocupacional.soc.Services.UnidadeOperacionalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/unidade-operacional")
public class UnidadeOperacionalController {

    private final UnidadeOperacionalService unidadeOperacionalService;

    public UnidadeOperacionalController(UnidadeOperacionalService unidadeOperacionalService) {
        this.unidadeOperacionalService = unidadeOperacionalService;
    }

    @PostMapping("/{empresaId}")
    public ResponseEntity<UnidadeOperacionalResponseDTO> criarUnidadeOperacional(
            @PathVariable Long empresaId,
            @Valid @RequestBody UnidadeOperacionalRequestDTO requestDTO) {
        try {
            UnidadeOperacionalResponseDTO responseDTO = unidadeOperacionalService.criar(empresaId, requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{unidadeId}")
    public ResponseEntity<UnidadeOperacionalResponseDTO> buscarUnidadeOperacionalPorId(@PathVariable Long unidadeId) {
        return unidadeOperacionalService.buscarPorId(unidadeId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade Operacional não encontrada com ID: " + unidadeId));
    }

    @GetMapping("/{empresaId}/unidades-empresa")
    public ResponseEntity<List<UnidadeOperacionalResponseDTO>> listarUnidadesPorEmpresa(@PathVariable Long empresaId) {
        try {
            List<UnidadeOperacionalResponseDTO> unidades = unidadeOperacionalService.listarPorEmpresaId(empresaId);
            return ResponseEntity.ok(unidades);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{unidadeId}")
    public ResponseEntity<UnidadeOperacionalResponseDTO> atualizarUnidadeOperacional(
            @PathVariable Long unidadeId,
            @Valid @RequestBody UnidadeOperacionalRequestDTO requestDTO) {
        try {
            UnidadeOperacionalResponseDTO updatedUnidade = unidadeOperacionalService.atualizar(unidadeId, requestDTO);
            return ResponseEntity.ok(updatedUnidade);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/unidades-operacionais/{unidadeId}")
    public ResponseEntity<Void> deletarUnidadeOperacional(@PathVariable Long unidadeId) {
        try {
            unidadeOperacionalService.deletar(unidadeId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
