package com.ocupacional.soc.Config;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Hibernate6Module hibernateModule() {
        // Alterado para Hibernate6Module
        Hibernate6Module module = new Hibernate6Module();

        // Esta linha é opcional, mas recomendada.
        // Ela previne que o Jackson force o carregamento de dados "lazy".
        module.disable(Hibernate6Module.Feature.FORCE_LAZY_LOADING);

        return module;
    }

}
