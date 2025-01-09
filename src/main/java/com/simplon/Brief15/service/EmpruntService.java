package com.simplon.Brief15.service;

import com.simplon.Brief15.entity.Emprunt;
import com.simplon.Brief15.repository.EmpruntRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpruntService {

    private final EmpruntRepository empruntRepository;

    public EmpruntService(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    public Emprunt saveEmprunt(Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    public List<Emprunt> saveAll(List<Emprunt> emprunts) {
        return empruntRepository.saveAll(emprunts);
    }

    public boolean isLivreEmprunte(Long livreId) {
        return empruntRepository.existsByLivreIdAndDateRetourIsNull(livreId);
    }

    public List<Emprunt> getEmpruntsByIds(List<Long> ids) {
        return empruntRepository.findAllById(ids);
    }

}
