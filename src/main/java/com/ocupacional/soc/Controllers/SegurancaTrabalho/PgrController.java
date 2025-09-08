package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.PgrResponseDTO;
import com.ocupacional.soc.Enuns.CadastroEmpresas.StatusEmpresa;
import com.ocupacional.soc.Services.SegurancaTrabalho.PgrService;
import com.ocupacional.soc.Services.impl.PgrServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/pgr")
@RequiredArgsConstructor
public class PgrController {

    private final PgrServiceImpl pgrService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PgrResponseDTO> createPgr(@RequestPart("pgr") @Valid PgrRequestDTO requestDTO, @RequestPart(name = "capa", required = false) MultipartFile capa) {
        return new ResponseEntity<>(pgrService.createPgr(requestDTO, capa), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PgrResponseDTO> getPgrById(@PathVariable Long id) {
        return ResponseEntity.ok(pgrService.getPgrById(id));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<Page<PgrResponseDTO>> getAllPgrsByEmpresa(
            @PathVariable Long empresaId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(pgrService.getAllPgrsByEmpresa(empresaId, pageable));
    }

    @GetMapping("/empresa/{empresaId}/status-filter")
    public ResponseEntity<Page<PgrResponseDTO>> getAllPgrsByEmpresaAndStatus(
            @PathVariable Long empresaId,
            @RequestParam(required = false) StatusEmpresa status,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(pgrService.getAllPgrsByEmpresaAndStatus(empresaId, status, pageable));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PgrResponseDTO> updatePgr(@PathVariable Long id, @RequestPart("pgr") @Valid PgrRequestDTO requestDTO, @RequestPart(name = "capa", required = false) MultipartFile capa) {
        return ResponseEntity.ok(pgrService.updatePgr(id, requestDTO, capa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePgr(@PathVariable Long id) {
        pgrService.deletePgr(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/relatorio")
    public ResponseEntity<byte[]> getPgrReport(@PathVariable Long id) {
        try {
            byte[] reportBytes = pgrService.gerarRelatorioPdf(id);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            // Sugere o nome do arquivo para o navegador
            headers.setContentDispositionFormData("filename", "PGR_ID_" + id + ".pdf");

            return new ResponseEntity<>(reportBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/inactivated")
    public ResponseEntity<PgrResponseDTO> inativarPgr(@PathVariable Long id){
        PgrResponseDTO pgr = pgrService.inactivatedPgr(id);
        return ResponseEntity.ok(pgr);
    }

}
