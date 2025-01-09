# Brief15-migration

## Objectif

Ce brief a pour objectifs de nous faire manipuler des entities avec JPA et de gérer la migration des tables avec flyway. Une api de gestion de bibliothèque est créée.

## Démarrage

1. Cloner le repo github
2. Si besoin, faire un `mvn clean install`
3. Une base h2 est embarquée et se crée au démarrage, il n'est donc pas nécessaire de créer de database pour tester l'application
3. Démarrer l'application
4. La console h2 est accessible ici : [console h2](http://localhost:8080/api/h2-console) (les paramètres de connexion sont dans `application.yml`)

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

### Migration Flyway
Dans le dossier resources/db/migration:
* V1__Create_Tables.sql : Création des tables sql dans la base h2 définie dans `application.yml`
* V2__Seeding_Tables.sql : Peupler les tables avec des données 
* Migration automatique au démarrage de l'application

## Difficultés

* Conception des entités : 
  * un emprunt concerne un livre ou un ensemble de livres? 
  * un emprunteur peut avoir plusieurs emprunts en cours? 
  * doit-on pouvoir filtrer les livres par genre?
  * des choix doivent être faits
* Création des emprunts:
  * beaucoup d'essais infructueux avant de trouver une solution avec les queryparams
* Rendre les livres:
  * difficulté due au choix d'avoir plusieurs livres possibles dans un emprunt
  * saveAll jamais utilisé avant
  * endpoint difficile à concevoir (mélange de queryparams et de route rest)

## Endpoints

* Genre

| Endpoint             | Operation | Réponse |
|----------------------|-----------|---------|
| "GET api/genre"      | findAll   | 200     |
| "GET api/genre/{id}" | findById  | 200     |
| "POST api/genre"     | create          | 201     |
| DELETE api/genre/{id} | delete | 204 |

* Livre

| Endpoint              | Operation | Réponse |
|-----------------------|-----------|---------|
| "GET api/livre"       | findAll   | 200     |
| "GET api/livre/{id}"  | findById  | 200     |
| "POST api/livre"      | create          | 201     |
| DELETE api/livre/{id} | delete | 204 |

* Emprunt

| Endpoint                                                                 | Operation | Réponse |
|--------------------------------------------------------------------------|-----------|---------|
| "POST api/emprunt"                                                       | createEmprunt   | 201     |
| "PUT api/emprunt/{emprunteurId}/rendre?empruntIds={livreId1},{livreId2}" | rendreLivres  | 200     |

## TESTS

Tous les endpoints on été testé avec useBruno. La collection de ces tests se trouve dans le dossier Bruno à la racine du projet.

