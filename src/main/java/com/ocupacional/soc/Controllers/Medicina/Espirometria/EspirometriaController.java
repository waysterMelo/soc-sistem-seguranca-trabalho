package com.ocupacional.soc.Controllers.Medicina.Espirometria;

import com.ocupacional.soc.Dto.Cadastros.ContextoProfissionalResponseDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoListDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Espirometria.EspirometriaAvaliacaoResponseDTO;
import com.ocupacional.soc.Services.Medicina.Espirometria.EspirometriaAvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/espirometria")
@RequiredArgsConstructor
public class EspirometriaController {

    private final EspirometriaAvaliacaoService espirometriaAvaliacaoService;

    @GetMapping("/contexto/{funcionarioId}")
    public ResponseEntity<ContextoProfissionalResponseDTO> getContexto(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok(espirometriaAvaliacaoService.findContextoProfissional(funcionarioId));
    }

    @PostMapping
    public ResponseEntity<EspirometriaAvaliacaoResponseDTO> create(@Valid @RequestBody EspirometriaAvaliacaoRequestDTO dto) {
        return new ResponseEntity<>(espirometriaAvaliacaoService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspirometriaAvaliacaoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody EspirometriaAvaliacaoRequestDTO dto) {
        return ResponseEntity.ok(espirometriaAvaliacaoService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspirometriaAvaliacaoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(espirometriaAvaliacaoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<EspirometriaAvaliacaoListDTO>> findAll(
            @PageableDefault(size = 10, sort = "dataExame") Pageable pageable,
            @RequestParam(required = false) Long empresaId,
            @RequestParam(required = false) Long setorId,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(espirometriaAvaliacaoService.findAll(pageable, empresaId, setorId, search));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        espirometriaAvaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}