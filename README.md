# Brief15-migration

## Objectif

Ce brief a pour objectifs de nous faire manipuler des entities avec JPA et de gérer la migration des tables avec flyway. Une api de gestion de bibliothèque est créée.

## Démarrage

1. Cloner le repo github
2. Si besoin, faire un `mvn clean install`
3. Démarrer l'application
4. La console h2 est accessible ici : [console h2](http://localhost:8080/api/h2-console)

## Fonctionnement

### Entities Jpa
* Livre 
* Genre
* Emprunteur
* Emprunt

### Relations
* Livre ↔ Genre : Relation @ManyToMany bidirectionnelle
* Livre ↔ Emprunt : Relation @ManyToOne.
* Genre ↔ Livre : Relation @ManyToMany bidirectionnelle.
* Emprunteur ↔ Emprunt : Relation @OneToMany.
* Emprunt ↔ Livre : Relation @ManyToOne.
* Emprunt ↔ Emprunteur : Relation @ManyToOne.

