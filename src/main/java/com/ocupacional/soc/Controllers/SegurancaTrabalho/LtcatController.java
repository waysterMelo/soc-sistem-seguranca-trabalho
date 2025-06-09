package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.LtcatResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.LtcatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ltcat")
@RequiredArgsConstructor
public class LtcatController {

    private final LtcatService ltcatService;

    @PostMapping
    public ResponseEntity<LtcatResponseDTO> createLtcat(@Valid @RequestBody LtcatRequestDTO dto) {
        return new ResponseEntity<>(ltcatService.createLtcat(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LtcatResponseDTO> getLtcatById(@PathVariable Long id) {
        return ResponseEntity.ok(ltcatService.getLtcatById(id));
    }

    @GetMapping
    public ResponseEntity<Page<LtcatResponseDTO>> getAllLtcats(Pageable pageable) {
        return ResponseEntity.ok(ltcatService.getAllLtcats(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LtcatResponseDTO> updateLtcat(@PathVariable Long id, @Valid @RequestBody LtcatRequestDTO dto) {
        return ResponseEntity.ok(ltcatService.updateLtcat(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLtcat(@PathVariable Long id) {
        ltcatService.deleteLtcat(id);
        return ResponseEntity.noContent().build();
    }
}