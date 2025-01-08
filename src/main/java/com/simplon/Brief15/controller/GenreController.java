package com.simplon.Brief15.controller;

import com.simplon.Brief15.entity.Genre;
import com.simplon.Brief15.service.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<List<Genre>> findAll() {
        List<Genre> genres = genreService.getAllGenres();
        if(genres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> findById(@PathVariable Long id) {
        Genre genre = genreService.getGenreById(id);
        if(genre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(genre);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Genre> create(@RequestBody Genre genre) {
        if (genre.getNom() == null) {
            return ResponseEntity.badRequest().build();
        }

        Genre existingGenre = genreService.getGenreByName(genre.getNom());
        if (existingGenre != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(existingGenre);
        }

        Genre createdGenre = genreService.saveGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Genre genre = genreService.getGenreById(id);
        if (genre == null) {
            return ResponseEntity.notFound().build();
        }
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }


}
