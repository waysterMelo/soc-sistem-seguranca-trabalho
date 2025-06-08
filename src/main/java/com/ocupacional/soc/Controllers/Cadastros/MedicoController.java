package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.MedicoEntity;
import com.ocupacional.soc.Services.Cadastros.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<MedicoEntity>> listarTodos() {
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoEntity> buscarPorId(@PathVariable Long id) {
        return medicoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicoEntity> criar(@RequestBody MedicoEntity medico) {
        return ResponseEntity.ok(medicoService.salvar(medico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoEntity> atualizar(@PathVariable Long id, @RequestBody MedicoEntity medico) {
        return ResponseEntity.ok(medicoService.atualizar(id, medico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 