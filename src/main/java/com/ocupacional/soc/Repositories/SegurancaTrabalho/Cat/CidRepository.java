package com.ocupacional.soc.Repositories.SegurancaTrabalho.Cat;

import com.ocupacional.soc.Entities.Cat.CidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CidRepository extends JpaRepository<CidEntity, Long> {

    Optional<CidEntity> findByCodigo(String codigo);

    List<CidEntity> findByDescricaoContainingIgnoreCase(String descricao);

    List<CidEntity> findByCodigoContainingIgnoreCase(String codigo);

    @Query("SELECT c FROM CidEntity c WHERE " +
           "LOWER(c.codigo) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.descricao) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<CidEntity> findByCodigoOrDescricaoContainingIgnoreCase(@Param("search") String search);
}
