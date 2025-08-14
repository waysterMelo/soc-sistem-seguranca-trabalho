package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Entities.Cadastros.RiscoTrabalhistaPgrEntity;
import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RiscoCatalogoRepository extends JpaRepository<RiscoCatalogoEntity, Long> {
    List<RiscoCatalogoEntity> findByGrupo(GrupoRisco grupo);

    @Query("SELECT r FROM RiscoCatalogoEntity r " +
            "WHERE LOWER(r.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    Page<RiscoCatalogoEntity> findByDescricao(
            @Param("descricao") String descricao,
            Pageable pageable);
}