package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.AgenteNocivoCatalogoResponseDTO;
import com.ocupacional.soc.Services.impl.AgenteNocivoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/agente-nocivo")
@RestController
@RequiredArgsConstructor
public class AgenteNocivoController {

    private final AgenteNocivoImpl service;

    @GetMapping
    public ResponseEntity<Page<AgenteNocivoCatalogoResponseDTO>> retornarTodos(Pageable pageable) {
        Page<AgenteNocivoCatalogoResponseDTO> agentes = service.listarAgentes(pageable);
        return ResponseEntity.ok(agentes);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<AgenteNocivoCatalogoResponseDTO>> buscarComFiltros(
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) String codigo,
            Pageable pageable) {

        Page<AgenteNocivoCatalogoResponseDTO> agentes =
                service.buscarComFiltros(descricao, codigo, pageable);
        return ResponseEntity.ok(agentes);
    }

    @GetMapping("/buscar/descricao")
    public ResponseEntity<Page<AgenteNocivoCatalogoResponseDTO>> buscarPorDescricao(
            @RequestParam String descricao,
            Pageable pageable) {

        Page<AgenteNocivoCatalogoResponseDTO> agentes =
                service.buscarPorDescricao(descricao, pageable);
        return ResponseEntity.ok(agentes);
    }

    @GetMapping("/buscar/codigo")
    public ResponseEntity<Page<AgenteNocivoCatalogoResponseDTO>> buscarPorCodigo(
            @RequestParam String codigo,
            Pageable pageable) {

        Page<AgenteNocivoCatalogoResponseDTO> agentes =
                service.buscarPorCodigo(codigo, pageable);
        return ResponseEntity.ok(agentes);
    }
}