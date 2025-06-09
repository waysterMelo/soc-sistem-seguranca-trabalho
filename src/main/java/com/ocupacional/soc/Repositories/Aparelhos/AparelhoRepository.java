package com.ocupacional.soc.Repositories.Aparelhos;

import com.ocupacional.soc.Entities.Aparelho.AparelhoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AparelhoRepository extends JpaRepository<AparelhoEntity, Long> {

    Page<AparelhoEntity> findByDescricaoContainingIgnoreCaseOrModeloContainingIgnoreCase(String descricao, String modelo, Pageable pageable);

}