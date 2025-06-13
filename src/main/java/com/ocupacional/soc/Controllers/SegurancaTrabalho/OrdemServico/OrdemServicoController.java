package com.ocupacional.soc.Controllers.SegurancaTrabalho.OrdemServico;

import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoListDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.OrdemServico.OrdemServicoResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.OrdemServico.OrdemServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordens-servico")
@RequiredArgsConstructor
public class OrdemServicoController {

    private final OrdemServicoService service;

    /**
     * Lista todas as Ordens de Serviço de forma paginada, com filtros.
     * Exemplo de Requisição:
     * GET /api/ordens-servico?page=0&size=10&sort=dataElaboracao,desc&funcionarioId=1
     */
    @GetMapping
    public ResponseEntity<Page<OrdemServicoListDTO>> findAll(
            @PageableDefault(size = 10, sort = "dataElaboracao") Pageable pageable,
            @RequestParam(required = false) Long funcionarioId,
            @RequestParam(required = false) String search) {
        return ResponseEntity.ok(service.findAll(pageable, funcionarioId, search));
    }

    /**
     * Busca uma Ordem de Serviço pelo seu ID.
     * Exemplo de Requisição:
     * GET /api/ordens-servico/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponseDTO> create(@Valid @RequestBody OrdemServicoRequestDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrdemServicoResponseDTO> update(@PathVariable Long id, @Valid @RequestBody OrdemServicoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}