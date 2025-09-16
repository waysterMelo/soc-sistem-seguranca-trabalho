package com.ocupacional.soc.Controllers.PrestadorServico;

import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoRequestDTO;
import com.ocupacional.soc.Dto.CadastroPrestadorServicos.PrestadorServicoResponseDTO;
import com.ocupacional.soc.Services.impl.PrestadorServicoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.TableGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/prestadores")
@RequiredArgsConstructor
@TableGenerator(name = "Prestadores de Serviço")
public class PrestadorServicoController {

    private final PrestadorServicoServiceImpl service;

    @PostMapping
    @Operation(summary = "Cadastrar prestador")
    public ResponseEntity<PrestadorServicoResponseDTO> create(
            @Valid @RequestBody PrestadorServicoRequestDTO dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar prestador por ID")
    public ResponseEntity<PrestadorServicoResponseDTO> findById(@PathVariable Long id) {
        // Supondo que seu service tenha um método findById que retorna um DTO
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public PrestadorServicoResponseDTO update(@PathVariable Long id,
                                              @Valid @RequestBody PrestadorServicoRequestDTO dto) {
        return service.update(id, dto);
    }

    @GetMapping
    public Page<PrestadorServicoResponseDTO> listar(
            @RequestParam(required = false, defaultValue = "") String q,
            @PageableDefault(size = 5, sort = "nome") Pageable pageable) {
        return service.list(q, pageable);
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importCsv(@RequestParam MultipartFile file) {
        service.importar(file);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


}
