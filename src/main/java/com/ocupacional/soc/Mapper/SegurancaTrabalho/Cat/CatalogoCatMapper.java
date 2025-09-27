package com.ocupacional.soc.Mapper.SegurancaTrabalho.Cat;

import org.springframework.stereotype.Component;

@Component
public class CatalogoCatMapper {
    public static <E> com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO toDto(E entity) {
        if (entity == null) return null;
        var dto = new com.ocupacional.soc.Dto.SegurancaTrabalho.Cat.CatalogoSimplesDTO();
        try {
            var idField = entity.getClass().getMethod("getId");
            var descField = entity.getClass().getMethod("getDescricao");
            dto.setId((Long) idField.invoke(entity));
            dto.setDescricao((String) descField.invoke(entity));
        } catch (Exception e) {
            // Lidar com a exceção ou logar
        }
        return dto;
    }
}