package com.simplon.Brief15.service;

import com.simplon.Brief15.entity.Livre;
import com.simplon.Brief15.repository.GenreRepository;
import com.simplon.Brief15.repository.LivreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService {

    private final LivreRepository livreRepository;
    private final GenreRepository genreRepository;

    public LivreService(LivreRepository livreRepository, GenreRepository genreRepository) {
        this.livreRepository = livreRepository;
        this.genreRepository = genreRepository;
    }

    public List<Livre> getAllLvres() {
        return livreRepository.findAll();
    }

    public Livre getLivreById(Long id) {
        return livreRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Ce livre n'existe pas")
        );
    }

    public Livre getLivreByTitle(String title) {
        return livreRepository.findByTitle(title);
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public void deleteLivreById(Long id) {
        if (!livreRepository.existsById(id)) {
            throw new EntityNotFoundException("Ce livre n'existe pas");
        }
        livreRepository.deleteById(id);
    }


}
