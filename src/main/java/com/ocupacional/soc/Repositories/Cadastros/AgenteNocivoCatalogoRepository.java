package com.ocupacional.soc.Repositories.Cadastros;

import com.ocupacional.soc.Entities.Cadastros.AgenteNocivoCatalogoEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenteNocivoCatalogoRepository extends JpaRepository<AgenteNocivoCatalogoEntity, Long> {


    @Query("SELECT a FROM AgenteNocivoCatalogoEntity a " +
            "WHERE LOWER(a.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))")
    Page<AgenteNocivoCatalogoEntity> findByDescricaoContainingIgnoreCase(
            @Param("descricao") String descricao,
            Pageable pageable);

    Page<AgenteNocivoCatalogoEntity> findByCodigoEsocial(
            String codigo,
            Pageable pageable);

    @Query("SELECT a FROM AgenteNocivoCatalogoEntity a " +
            "WHERE (:descricao IS NULL OR LOWER(a.descricao) LIKE LOWER(CONCAT('%', :descricao, '%'))) " +
            "AND (:codigo IS NULL OR LOWER(a.codigoEsocial) LIKE LOWER(CONCAT('%', :codigo, '%')))")
    Page<AgenteNocivoCatalogoEntity> findByDescricaoOrCodigo(
            @Param("descricao") String descricao,
            @Param("codigo") String codigo,
            Pageable pageable);



}