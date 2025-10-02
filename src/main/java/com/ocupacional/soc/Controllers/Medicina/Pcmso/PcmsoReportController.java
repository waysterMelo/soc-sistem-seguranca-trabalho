package com.ocupacional.soc.Controllers.Medicina.Pcmso;


import com.ocupacional.soc.Dto.Medicina.Pcmso.PcmsoResponseDTO;
import com.ocupacional.soc.Services.Medicina.Pcmso.PcmsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/report/pcmso")
public class PcmsoReportController {

    @Autowired
    private PcmsoService pcmsoService;

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/{id}")
    @ResponseBody
    public String generatePcmsoReport(@PathVariable Long id) {
        PcmsoResponseDTO pcmso = pcmsoService.findById(id);
        Context context = new Context();
        context.setVariable("pcmso", pcmso);
        return templateEngine.process("pcmso_report", context);
    }
}
