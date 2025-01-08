package com.simplon.Brief15.service;

import com.simplon.Brief15.entity.Genre;
import com.simplon.Brief15.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Genre inconnu")
        );
    }

    public Genre getGenreByName(String name) {
        return genreRepository.findByNom(name);
    }

    public Genre saveGenre(Genre genre) {
        if (genre.getNom() == null || genre.getNom().isEmpty()) {
            throw new IllegalArgumentException("Le nom du genre ne peut pas être vide");
        }
        return genreRepository.save(genre);
    }

    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new EntityNotFoundException("Cet élément n'existe pas");
        }
        genreRepository.deleteById(id);
    }
}
