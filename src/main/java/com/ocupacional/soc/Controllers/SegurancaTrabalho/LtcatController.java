package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat.LtcatRequestDTO;
import com.ocupacional.soc.Dto.SegurancaTrabalho.Ltcat.LtcatResponseDTO;
import com.ocupacional.soc.Services.SegurancaTrabalho.Ltcat.LtcatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ltcat")
@RequiredArgsConstructor
public class LtcatController {

    private final LtcatService ltcatService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LtcatResponseDTO> createLtcat(
            @Valid @RequestPart("ltcat") LtcatRequestDTO dto,
            @RequestPart(value = "imagemCapa", required = false) MultipartFile imagemCapa) {
        return new ResponseEntity<>(ltcatService.createLtcat(dto, imagemCapa), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LtcatResponseDTO> getLtcatById(@PathVariable Long id) {
        return ResponseEntity.ok(ltcatService.getLtcatById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<LtcatResponseDTO>> getAllLtcats(Pageable pageable) {
        return ResponseEntity.ok(ltcatService.getAllLtcats(pageable));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LtcatResponseDTO> updateLtcat(
            @PathVariable Long id,
            @Valid @RequestPart("ltcat") LtcatRequestDTO dto,
            @RequestPart(value = "imagemCapa", required = false) MultipartFile imagemCapa) {
        return ResponseEntity.ok(ltcatService.updateLtcat(id, dto, imagemCapa));
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLtcat(@PathVariable Long id) {
        ltcatService.deleteLtcat(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/report")
    public ModelAndView getLtcatReport(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("ltcat");
        LtcatResponseDTO ltcatDto = ltcatService.getLtcatById(id);
        mav.addObject("ltcat", ltcatDto);
        return mav;
    }
}