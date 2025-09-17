package com.ocupacional.soc.Controllers.SegurancaTrabalho;

import com.ocupacional.soc.Dto.Relatorios.PgrRelatorioDTO;
import com.ocupacional.soc.Entities.SegurancaTrabalho.Pgr.PgrEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.UnidadeOperacionalMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.PgrMapaRiscoFuncaoMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.PlanoAcaoRiscoMapper;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.PgrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports/pgr")
@RequiredArgsConstructor
public class PgrReportController {

    private final PgrRepository pgrRepository;
    private final PgrMapaRiscoFuncaoMapper mapaRiscoMapper;
    private final PlanoAcaoRiscoMapper planoAcaoMapper;
    private final UnidadeOperacionalMapper unidadeOperacionalMapper;

    @GetMapping("/{pgrId}")
    public String gerarRelatorioHtml(@PathVariable Long pgrId, Model model) {
        PgrEntity pgr = pgrRepository.findById(pgrId)
                .orElseThrow(() -> new ResourceNotFoundException("PGR não encontrado com ID: " + pgrId));

        PgrRelatorioDTO pgrRelatorioDTO = new PgrRelatorioDTO(pgr, unidadeOperacionalMapper, mapaRiscoMapper, planoAcaoMapper);

        model.addAttribute("pgr", pgrRelatorioDTO);

        return "pgr_report";
    }
}
