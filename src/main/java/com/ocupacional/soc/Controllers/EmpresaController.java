package com.ocupacional.soc.Controllers;

import com.ocupacional.soc.Dto.EmpresaDto;
import com.ocupacional.soc.Entities.EmpresaEntity;
import com.ocupacional.soc.Services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<EmpresaEntity>> listarTodas() {
        return ResponseEntity.ok(empresaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaEntity> buscarPorId(@PathVariable Long id) {
        return empresaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmpresaEntity> criar(@RequestBody EmpresaEntity empresa) {
        return ResponseEntity.ok(empresaService.salvar(empresa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaEntity> atualizar(@PathVariable Long id, @RequestBody EmpresaEntity empresa) {
        return ResponseEntity.ok(empresaService.atualizar(id, empresa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 