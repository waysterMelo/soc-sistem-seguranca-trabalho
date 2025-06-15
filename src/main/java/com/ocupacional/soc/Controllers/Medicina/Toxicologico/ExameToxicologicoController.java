package com.ocupacional.soc.Controllers.Medicina.Toxicologico;

import com.ocupacional.soc.Dto.Medicina.Toxicologico.ExameToxicologicoRequestDTO;
import com.ocupacional.soc.Dto.Medicina.Toxicologico.ExameToxicologicoResponseDTO;
import com.ocupacional.soc.Services.Medicina.Toxicologico.ExameToxicologicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@RequestMapping("/api/exames-toxicologicos")
@RequiredArgsConstructor
public class ExameToxicologicoController {

    private final ExameToxicologicoService exameToxicologicoService;

    @GetMapping
    @Operation(summary = "Lista todos os exames toxicológicos de forma paginada")
    public ResponseEntity<Page<ExameToxicologicoResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "dataExame") Pageable pageable,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(exameToxicologicoService.findAll(pageable, search));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um exame toxicológico pelo ID")
    public ResponseEntity<ExameToxicologicoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(exameToxicologicoService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Cria um novo registro de exame toxicológico",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(examples = {
                    @ExampleObject(name = "Exemplo de Requisição", value =
                            "{\n" +
                                    "  \"registroProfissionalId\": 1,\n" +
                                    "  \"dataExame\": \"2025-06-15\",\n" +
                                    "  \"codigoExame\": \"AA213212\",\n" +
                                    "  \"laboratorioId\": 1,\n" +
                                    "  \"medicoResponsavelId\": 1,\n" +
                                    "  \"estado\": \"AM\",\n" +
                                    "  \"tipoRetificacao\": \"ORIGINAL\",\n" +
                                    "  \"numeroReciboEsocial\": null\n" +
                                    "}"
                    )
            }))
    )
    public ResponseEntity<ExameToxicologicoResponseDTO> create(@Valid @RequestBody ExameToxicologicoRequestDTO dto) {
        return new ResponseEntity<>(exameToxicologicoService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um exame toxicológico existente")
    public ResponseEntity<ExameToxicologicoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ExameToxicologicoRequestDTO dto) {
        return ResponseEntity.ok(exameToxicologicoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui um exame toxicológico (soft delete)")
    @ApiResponse(responseCode = "204", description = "Exame excluído com sucesso")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exameToxicologicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}