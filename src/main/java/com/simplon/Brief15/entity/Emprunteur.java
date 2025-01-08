package com.simplon.Brief15.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emprunteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Column(unique=true)
    private String email;

    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private Set<Emprunt> emprunts = new HashSet<>();

}
