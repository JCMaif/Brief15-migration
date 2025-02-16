package com.simplon.Brief15.repository;

import com.simplon.Brief15.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    Livre findByTitle(String title);
}
