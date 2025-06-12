package com.ocupacional.soc.Controllers.EpiController;

import com.ocupacional.soc.Dto.EpiDto.MovimentacaoEpiRequestDTO;
import com.ocupacional.soc.Dto.EpiDto.MovimentacaoEpiResponseDTO;
import com.ocupacional.soc.Entities.Epi.MovimentacaoEpiEntity;
import com.ocupacional.soc.Mapper.EpiMappers.MovimentacaoEpiMapper;
import com.ocupacional.soc.Repositories.Epi.MovimentacaoEpiRepository;
import com.ocupacional.soc.Services.impl.MovimentacaoEpiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes-epi")
@RequiredArgsConstructor
public class MovimentacaoEpiController {

    private final MovimentacaoEpiServiceImpl service;
    private final MovimentacaoEpiRepository repository; // Para consultas simples
    private final MovimentacaoEpiMapper movimentacaoEpiMapper;


    @PostMapping("/retirada")
    public ResponseEntity<List<MovimentacaoEpiResponseDTO>> registrarRetirada(@RequestBody MovimentacaoEpiRequestDTO dto) {
        List<MovimentacaoEpiResponseDTO> movimentacoesCriadas = service.registrarRetirada(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacoesCriadas);
    }


    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<MovimentacaoEpiResponseDTO>>
    getMovimentacoesPorFuncionario(@PathVariable Long funcionarioId) {
        List<MovimentacaoEpiEntity> movimentacoes =
                repository.findByFuncionarioIdWithDetails(funcionarioId);
        List<MovimentacaoEpiResponseDTO> response = movimentacoes.stream().map(movimentacaoEpiMapper::toDto)
                .toList();
        return ResponseEntity.ok(response);
    }
}
