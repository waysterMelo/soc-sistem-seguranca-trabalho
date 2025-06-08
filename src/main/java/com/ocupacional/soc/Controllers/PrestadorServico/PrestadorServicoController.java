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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/prestadores")
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

    @PutMapping("/{id}")
    public PrestadorServicoResponseDTO update(@PathVariable Long id,
                                              @Valid @RequestBody PrestadorServicoRequestDTO dto) {
        return service.update(id, dto);
    }

    @GetMapping
    public Page<PrestadorServicoResponseDTO> list(
            @RequestParam(defaultValue = "") String q,
            Pageable pageable) {
        return service.list(q, pageable);
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importCsv(@RequestParam MultipartFile file) {
        service.importar(file);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }


}
