package com.simplon.Brief15.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    @ManyToOne
    @JoinColumn(name = "emprunteur_id", nullable = false)
    private Emprunteur emprunteur;

    @ManyToOne
    @JoinColumn(name = "livre_id", nullable = false)
    private Livre livre;
}
