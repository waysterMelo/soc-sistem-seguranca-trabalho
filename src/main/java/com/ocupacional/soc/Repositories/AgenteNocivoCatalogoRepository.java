package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.AgenteNocivoCatalogoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgenteNocivoCatalogoRepository extends JpaRepository<AgenteNocivoCatalogoEntity, Long>  {

    boolean existsByCodigoEsocial(String codigoEsocial);
    Optional<AgenteNocivoCatalogoEntity> findByCodigoEsocial(String codigoEsocial);

    // Para busca paginada
    Page<AgenteNocivoCatalogoEntity> findByDescricaoContainingIgnoreCaseOrCodigoEsocialContainingIgnoreCase(String descricao, String codigoEsocial, Pageable pageable);

    // Para listar todos com filtro (sem paginação)
    List<AgenteNocivoCatalogoEntity> findByDescricaoContainingIgnoreCaseOrCodigoEsocialContainingIgnoreCase(String descricao, String codigoEsocial);
}
