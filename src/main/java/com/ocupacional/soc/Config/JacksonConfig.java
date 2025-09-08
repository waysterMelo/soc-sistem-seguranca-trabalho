package com.ocupacional.soc.Config;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Hibernate6Module hibernateModule() {
        Hibernate6Module module = new Hibernate6Module();

        module.disable(Hibernate6Module.Feature.FORCE_LAZY_LOADING);

        return module;
    }

}
