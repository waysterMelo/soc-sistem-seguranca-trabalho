package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.CnaeEntity;
import com.ocupacional.soc.Services.Cadastros.CnaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cnaes")
public class CnaeController {

    @Autowired
    private CnaeService cnaeService;

    @GetMapping
    public ResponseEntity<List<CnaeEntity>> listarTodos() {
        return ResponseEntity.ok(cnaeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CnaeEntity> buscarPorId(@PathVariable Long id) {
        return cnaeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CnaeEntity> criar(@RequestBody CnaeEntity cnae) {
        return ResponseEntity.ok(cnaeService.salvar(cnae));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CnaeEntity> atualizar(@PathVariable Long id, @RequestBody CnaeEntity cnae) {
        return ResponseEntity.ok(cnaeService.atualizar(id, cnae));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        cnaeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 