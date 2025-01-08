package com.simplon.Brief15.repository;

import com.simplon.Brief15.entity.Emprunteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprunteurRepository extends JpaRepository<Emprunteur, Long> {
}
