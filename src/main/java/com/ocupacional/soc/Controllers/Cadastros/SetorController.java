package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.SetorComFuncoesDTO;
import com.ocupacional.soc.Dto.Cadastros.SetorRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.SetorResponseDTO;
import com.ocupacional.soc.Services.Cadastros.SetorService;
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
@RequestMapping("/setores")
public class SetorController {

    private final SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

    @PostMapping
    public ResponseEntity<SetorResponseDTO> criarSetor(@Valid @RequestBody SetorRequestDTO requestDTO) {
       try {
           SetorResponseDTO setorResponseDTO = setorService.criar(requestDTO);
           return new ResponseEntity<>(setorResponseDTO, HttpStatus.CREATED);
       }catch (EntityNotFoundException e){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
       }catch (IllegalArgumentException ex){
           throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
       }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> buscarSetorPorId(@PathVariable Long id) {
        return setorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor " +
                        "não encontrado com ID " + id));
    }


    @GetMapping
    public ResponseEntity<List<SetorResponseDTO>> listarSetores(@RequestParam(required = false)
                                                                 Long empresaId) {
        List<SetorResponseDTO> setores;
        if (empresaId != null) {
            try {
                setores = setorService.listarPorEmpresa(empresaId);
            } catch (EntityNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        } else {
            setores = setorService.listarTodos();
        }
        // Não lançar 404 se a lista global estiver vazia, apenas retornar lista vazia.
        // Lançar 404 apenas se o recurso específico (como empresaId) não for encontrado.
        return ResponseEntity.ok(setores);
    }

    @GetMapping("/buscar-por-empresa")
    public ResponseEntity<SetorResponseDTO> buscarSetorPorNomeEEmpresa(
            @RequestParam String nome,
            @RequestParam Long empresaId) {
        try {
            return setorService.buscarPorNomeEEmpresa(nome, empresaId)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Setor com nome '" + nome + "' não encontrado na empresa ID: " + empresaId));
        } catch (EntityNotFoundException e) { // Captura EntityNotFoundException vinda do serviço (ex: empresa não encontrada)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/com-funcoes")
    public ResponseEntity<List<SetorComFuncoesDTO>> listarSetoresComFuncoes(@RequestParam Long empresaId) {
        try {
            List<SetorComFuncoesDTO> setoresComFuncoes = setorService.listarSetoresComFuncoesPorEmpresa(empresaId);
            return ResponseEntity.ok(setoresComFuncoes);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<SetorResponseDTO> atualizarSetor(@PathVariable Long id, @Valid @RequestBody SetorRequestDTO requestDTO) {
        try {
            SetorResponseDTO atualizarSetor = setorService.atualizar(id, requestDTO);
            return ResponseEntity.ok(atualizarSetor);
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
        } catch (IllegalStateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
