package com.ocupacional.soc.Controllers;

import com.ocupacional.soc.Dto.SetorRequestDTO;
import com.ocupacional.soc.Dto.SetorResponseDTO;
import com.ocupacional.soc.Services.SetorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public ResponseEntity<SetorResponseDTO> criarSetor(@Valid @RequestBody SetorRequestDTO requestDTO) {
        try {
            SetorResponseDTO responseDTO = setorService.criar(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> buscarSetorPorId(@PathVariable Long id) {
        return setorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado com ID: " + id));
    }

    @GetMapping
    public ResponseEntity<List<SetorResponseDTO>> listarTodosSetores() {
        List<SetorResponseDTO> setores = setorService.listarTodos();
        return ResponseEntity.ok(setores);
    }

    @GetMapping("/buscar")
    public ResponseEntity<SetorResponseDTO> buscarSetorPorNome(@RequestParam String nome) {
        return setorService.buscarPorNome(nome)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor não encontrado com nome: " + nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> atualizarSetor(@PathVariable Long id, @Valid @RequestBody SetorRequestDTO requestDTO) {
        try {
            SetorResponseDTO updatedSetor = setorService.atualizar(id, requestDTO);
            return ResponseEntity.ok(updatedSetor);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSetor(@PathVariable Long id) {
        try {
            setorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

}
