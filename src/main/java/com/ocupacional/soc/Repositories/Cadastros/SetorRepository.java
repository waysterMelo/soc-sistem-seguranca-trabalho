package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.SetorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SetorRepository extends JpaRepository<SetorEntity, Long> {

    Optional<SetorEntity> findByNomeAndEmpresaId(String nome, Long empresaId);

    List<SetorEntity> findByEmpresaId(Long empresaId);

}
