package com.ocupacional.soc.Repositories;

import com.ocupacional.soc.Entities.BibliografiaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibliografiaRepository extends JpaRepository<BibliografiaEntity, Long> {
    Page<BibliografiaEntity> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCase(String titulo, String autor, Pageable pageable);
}