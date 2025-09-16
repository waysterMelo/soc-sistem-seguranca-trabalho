package com.ocupacional.soc.Controllers.Cadastros;

import com.ocupacional.soc.Dto.Cadastros.CboRequestDTO;
import com.ocupacional.soc.Dto.Cadastros.CboResponseDTO;
import com.ocupacional.soc.Services.impl.CboServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cbo")
@RequiredArgsConstructor
public class CboController {


    private final CboServiceImpl cboService;


    @PostMapping
    public ResponseEntity<CboResponseDTO> create(@RequestBody CboRequestDTO dto) {
        CboResponseDTO created = cboService.create(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CboResponseDTO> getById(@PathVariable Long id) {
        CboResponseDTO cbo = cboService.findById(id);
        return ResponseEntity.ok(cbo);
    }

    @GetMapping
    public ResponseEntity<List<CboResponseDTO>> getAll() {
        List<CboResponseDTO> list = cboService.findAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CboResponseDTO> update(@PathVariable Long id, @RequestBody CboRequestDTO dto) {
        CboResponseDTO updated = cboService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cboService.delete(id);
        return ResponseEntity.noContent().build();
    }
}