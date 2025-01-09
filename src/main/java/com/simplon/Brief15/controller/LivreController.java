package com.simplon.Brief15.controller;

import com.simplon.Brief15.entity.Livre;
import com.simplon.Brief15.service.LivreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("livre")
public class LivreController {
    private final LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public ResponseEntity<List<Livre>> findAll() {
        List<Livre> livres = livreService.getAllLvres();
        if (livres.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> findById(@PathVariable Long id) {
        Livre livre = livreService.getLivreById(id);
        if (livre == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(livre);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Livre> create(@RequestBody Livre livre) {
        if (livre == null) {
            return ResponseEntity.badRequest().build();
        }
        Livre exitingLivre = livreService.getLivreByTitle(livre.getTitle());
        if (exitingLivre != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exitingLivre);
        }
        Livre createdLivre = livreService.saveLivre(livre);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLivre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Livre livre = livreService.getLivreById(id);
        if (livre == null) {
            return ResponseEntity.notFound().build();
        }
        livreService.deleteLivreById(id);
        return ResponseEntity.noContent().build();
    }
}
