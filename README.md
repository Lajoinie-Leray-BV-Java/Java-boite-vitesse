# Boîte de Vitesse REST API

Application Spring Boot exposant une API REST pour gérer des boîtes de vitesses de manière remote.

## Table des matières

1. [Architecture de l'application](#1-architecture-de-lapplication)
2. [Construction de l'application](#2-construction-de-lapplication)
3. [Lancement de l'application](#3-lancement-de-lapplication)
4. [Utilisation de l'API REST](#4-utilisation-de-lapi-rest)
5. [Tests de l'application](#5-tests-de-lapplication)

---

## 1. Architecture de l'application

### Vue d'ensemble

L'application suit une architecture en couches Spring Boot classique :

```
Controller (REST) - API REST
Services          - Logique métier
Repository (JPA)  - Accès données
Base H2 (mémoire) - Persistance
```

### Composants principaux

**Modèles** (`models/`)
- `GearBox` : Entité JPA représentant une boîte de vitesse avec son ID et sa vitesse actuelle
- `BaseGear` et ses implémentations (`GN`, `GR`, `G1` à `G4`) : Représentent les différentes vitesses et gèrent les transitions autorisées

**Contrôleur** (`controllers/`)
- `GearBoxController` : Expose les endpoints REST, gère les requêtes HTTP et délègue au service

**Service** (`services/`)
- `GearBoxService` : Contient la logique métier (création, suppression, changement de vitesse)

**Repository** (`repository/`)
- `GearRepository` : Interface Spring Data JPA héritant de `JpaRepository` pour les opérations CRUD

**Gestion des erreurs** (`common/`)
- `GearException` : Exception métier levée lors de transitions de vitesse invalides (passer directement de G1 à G4 par exemple)

### Technologies utilisées

- **Spring Boot** avec Spring Web MVC et Spring Data JPA
- **H2 Database** (base en mémoire)
- **Lombok** pour la génération de code
- **Java 21**
- **Maven** pour la gestion des dépendances

---

## 2. Construction de l'application

### Prérequis

- **Java 21** ou supérieur
- **Maven 3.6+**

### Compilation

**Avec Maven global :**
```bash
mvn clean install
```

Le JAR généré se trouve dans `target/gearbox-1.2-SNAPSHOT.jar`

---

## 3. Lancement de l'application

**Option 1 : Avec Maven**
```bash
./mvnw spring-boot:run
```

**Option 2 : Depuis un IDE comme IntelliJ**
- Exécuter la classe `GearboxApplication.java`

L'application démarre sur **http://localhost:8080**

---

## 4. Utilisation de l'API REST

### Endpoints disponibles

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| **GET** | `/gearBox` | Liste toutes les boîtes |
| **GET** | `/gearBox/{id}` | Récupère une boîte par ID |
| **POST** | `/gearBox` | Crée une nouvelle boîte |
| **PUT** | `/gearBox/{id}/to/{gear}` | Change la vitesse |
| **DELETE** | `/gearBox/{id}` | Supprime une boîte |

### Exemples d'utilisation

**Créer une boîte (point mort par défaut)**
```bash
POST http://localhost:8080/gearBox
```

**Lister toutes les boîtes**
```bash
GET http://localhost:8080/gearBox
```

**Changer de vitesse**
```bash
PUT http://localhost:8080/gearBox/1/to/G2
```

**Supprimer une boîte**
```bash
DELETE http://localhost:8080/gearBox/1
```

### Règles de transition

Les transitions suivent la logique attendue :

| Depuis | Vers autorisées |
|--------|-----------------|
| GN (Point mort) | G1, G3, GR |
| GR (Marche arrière) | GN |
| G1 | GN, GR |
| G2 | GN |
| G3 | G2, G4 |
| G4 | G3 |

Toute transition non listée sera rejetée avec une erreur **400 Bad Request**.

### Codes HTTP

- **200** : Succès (GET, PUT)
- **204** : Suppression réussie (DELETE)
- **400** : Transition invalide ou données incorrectes
- **404** : Ressource introuvable

---

## 5. Tests de l'application

### Stratégie de tests
*(Si nous devions en mettre en place)*

**Tests unitaires du service (`GearBoxService`)**
- Création d'une boîte avec vitesse par défaut (GN)
- Changement de vitesse valide (GN → G1 → GR)
- Changement de vitesse invalide doit lever une `GearException` (G1 → G4)
- Suppression d'une boîte

**Tests d'intégration du contrôleur (`GearBoxController`)**
- GET `/gearBox` retourne une liste vide au démarrage
- POST `/gearBox` crée une nouvelle boîte et retourne 200
- PUT avec transition valide retourne 200
- PUT avec transition invalide retourne 400
- DELETE supprime la boîte et retourne 204

---

## Auteur

Projet réalisé dans le cadre du M1 DEV-A 2025-2026 à Sup de Vinci Bordeaux.
>> Elie LAJOINIE & Thibault LERAY
