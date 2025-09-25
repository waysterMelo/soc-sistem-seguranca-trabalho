package com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Entities.Cat.NaturezaLesaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NaturezaLesaoRepository extends JpaRepository<NaturezaLesaoEntity, Long> {
    List<NaturezaLesaoEntity> findByDescricaoContainingIgnoreCase(String descricao);
}
