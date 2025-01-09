package com.simplon.Brief15.controller;

import com.simplon.Brief15.entity.Emprunt;
import com.simplon.Brief15.entity.Emprunteur;
import com.simplon.Brief15.entity.Livre;
import com.simplon.Brief15.service.EmpruntService;
import com.simplon.Brief15.service.EmprunteurService;
import com.simplon.Brief15.service.LivreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {

    private final EmpruntService empruntService;
    private final EmprunteurService emprunteurService;
    private final LivreService livreService;

    public EmpruntController(EmpruntService empruntService, EmprunteurService emprunteurService, LivreService livreService) {
        this.empruntService = empruntService;
        this.emprunteurService = emprunteurService;
        this.livreService = livreService;
    }

    @PostMapping
    public ResponseEntity<Emprunt> createEmprunt(@RequestParam Long emprunteurId, @RequestParam Long livreId) {
        Emprunteur emprunteur = emprunteurService.findById(emprunteurId);
        Livre livre = livreService.getLivreById(livreId);

        if ( empruntService.isLivreEmprunte(livreId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        Emprunt emprunt = new Emprunt();
        emprunt.setEmprunteur(emprunteur);
        emprunt.setLivre(livre);
        emprunt.setDateEmprunt(LocalDate.now());

        Emprunt createdEmprunt = empruntService.saveEmprunt(emprunt);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmprunt);
    }

    @PutMapping("/{emprunteurId}/rendre")
    public ResponseEntity<List<Emprunt>> rendreLivres(@PathVariable Long emprunteurId, @RequestParam List<Long> empruntIds) {

        Emprunteur emprunteur = emprunteurService.findById(emprunteurId);
        List<Emprunt> emprunts = empruntService.getEmpruntsByIds(empruntIds);

        emprunts.forEach(emprunt -> emprunt.setDateRetour(LocalDate.now()));
        List<Emprunt> updatedEmprunts = empruntService.saveAll(emprunts);

        return ResponseEntity.ok(updatedEmprunts);
    }
}
