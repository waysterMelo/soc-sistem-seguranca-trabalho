package com.ocupacional.soc.Controllers.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO;
import com.ocupacional.soc.Services.impl.AgenteCausadorServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agente-causador")
@RequiredArgsConstructor
public class AgenteCausadorController {

    private final AgenteCausadorServiceImpl agenteCausadorService;

    @GetMapping
    public ResponseEntity<List<CatalogoSimplesDTO>> getAll() {
        List<CatalogoSimplesDTO> list = agenteCausadorService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogoSimplesDTO> getById(@PathVariable Long id) {
        CatalogoSimplesDTO agenteCausador = agenteCausadorService.findById(id);
        return ResponseEntity.ok(agenteCausador);
    }

    @GetMapping("/buscar-descricao")
    public ResponseEntity<List<CatalogoSimplesDTO>> searchByDescricao(@RequestParam String descricao) {
        List<CatalogoSimplesDTO> list = agenteCausadorService.findByDescricao(descricao);
        return ResponseEntity.ok(list);
    }
}