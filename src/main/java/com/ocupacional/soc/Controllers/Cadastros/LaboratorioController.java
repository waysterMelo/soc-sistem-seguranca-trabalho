package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.LaboratorioDTO;
import com.ocupacional.soc.Services.Cadastros.LaboratorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/laboratorios")
@RequiredArgsConstructor
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    @GetMapping
    @Operation(summary = "Lista todos os laboratórios de forma paginada, com filtro opcional por nome ou razão social.")
    public ResponseEntity<Page<LaboratorioDTO>> findAll(
            @PageableDefault(size = 10, sort = "nomeFantasia") Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(laboratorioService.findAll(pageable, search));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um laboratório pelo seu ID.")
    public ResponseEntity<LaboratorioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(laboratorioService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Cria um novo laboratório.")
    @ApiResponse(responseCode = "201", description = "Laboratório criado com sucesso.")
    public ResponseEntity<LaboratorioDTO> create(@Valid @RequestBody LaboratorioDTO dto) {
        return new ResponseEntity<>(laboratorioService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um laboratório existente.")
    public ResponseEntity<LaboratorioDTO> update(@PathVariable Long id, @Valid @RequestBody LaboratorioDTO dto) {
        return ResponseEntity.ok(laboratorioService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um laboratório (soft delete).")
    @ApiResponse(responseCode = "204", description = "Laboratório excluído com sucesso.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laboratorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}