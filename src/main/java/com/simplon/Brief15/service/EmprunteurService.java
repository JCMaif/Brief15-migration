package com.simplon.Brief15.service;

import com.simplon.Brief15.entity.Emprunteur;
import com.simplon.Brief15.repository.EmprunteurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmprunteurService {

    private final EmprunteurRepository emprunteurRepository;


    public EmprunteurService(EmprunteurRepository emprunteurRepository) {
        this.emprunteurRepository = emprunteurRepository;
    }

    public Emprunteur findById(Long id) {
        return emprunteurRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Emprunteur inconnu")
        );
    }

    public Emprunteur save(Emprunteur emprunteur) {
        return emprunteurRepository.save(emprunteur);
    }
}
