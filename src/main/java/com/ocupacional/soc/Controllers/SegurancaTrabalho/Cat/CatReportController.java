package com.ocupacional.soc.Controllers.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Dto.Relatorios.CatRelatorioDTO;
import com.ocupacional.soc.Entities.Cat.CatEntity;
import com.ocupacional.soc.Exceptions.ResourceNotFoundException;
import com.ocupacional.soc.Mapper.Cadastros.EnderecoMapper;
import com.ocupacional.soc.Mapper.Cadastros.FuncionarioMapper;
import com.ocupacional.soc.Mapper.PrestadorServicos.PrestadorServicoMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CatalogoCatMapper;
import com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat.CidMapper;
import com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat.CatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reports/cat")
@RequiredArgsConstructor
public class CatReportController {

    private final CatRepository catRepository;
    private final FuncionarioMapper funcionarioMapper;
    private final EnderecoMapper enderecoMapper;
    private final CatalogoCatMapper catalogoCatMapper;
    private final CidMapper cidMapper;
    private final PrestadorServicoMapper prestadorServicoMapper;

    @GetMapping("/{catId}")
    public String gerarRelatorioHtml(@PathVariable Long catId, Model model) {
        CatEntity cat = catRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("CAT não encontrada com ID: " + catId));

        CatRelatorioDTO catRelatorioDTO = new CatRelatorioDTO(
            cat,
            funcionarioMapper,
            enderecoMapper,
            catalogoCatMapper,
            cidMapper,
            prestadorServicoMapper
        );

        model.addAttribute("cat", catRelatorioDTO);

        return "cat_report";
    }
}