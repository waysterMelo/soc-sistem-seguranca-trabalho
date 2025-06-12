package com.ocupacional.soc.Controllers.SegurancaTrabalho.Treinamento;


import com.ocupacional.soc.Dto.SegurancaTrabalho.Treinamento.TreinamentoCatalogoDTO;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Services.SegurancaTrabalho.Treinamento.TreinamentoCatalogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/treinamentos/catalogo")
@RequiredArgsConstructor
public class TreinamentoCatalogoController {

    private final TreinamentoCatalogoService service;

    @GetMapping
    public ResponseEntity<List<TreinamentoCatalogoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoCatalogoDTO> pesquisarPorId(@PathVariable Long id) {
        TreinamentoCatalogoDTO dto = service.getById(id)
                .stream()
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Treinamento (Catálogo) não encontrado: " + id));
        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<TreinamentoCatalogoDTO> create(@Valid @RequestBody TreinamentoCatalogoDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinamentoCatalogoDTO> update(@PathVariable Long id, @Valid @RequestBody TreinamentoCatalogoDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}