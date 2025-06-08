package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.RiscoCatalogoEntity;
import com.ocupacional.soc.Enuns.CadastroFuncoes.GrupoRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RiscoCatalogoRepository extends JpaRepository<RiscoCatalogoEntity, Long> {
    List<RiscoCatalogoEntity> findByGrupo(GrupoRisco grupo);
}