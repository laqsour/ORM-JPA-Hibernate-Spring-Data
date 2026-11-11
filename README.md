# Gestion Produits - Spring Boot

Application Web JEE de gestion de produits avec Spring Boot, Spring MVC, Spring Data JPA, Hibernate, H2, MySQL, Thymeleaf, Bootstrap, Validation et Spring Security.

## Prérequis
- JDK 21
- IntelliJ IDEA (ou VS Code)
- Maven (IntelliJ peut utiliser le Maven Wrapper si présent; sinon Maven local)

## Ouvrir et lancer dans IntelliJ
1. Décompresser `gestion-produits.zip`.
2. Dans IntelliJ, choisir **File > Open** puis sélectionner le dossier `gestion-produits` (celui qui contient `pom.xml`).
3. Si IntelliJ demande de charger le projet Maven, choisir **Load** / **Load Maven Project**.
4. Attendre le téléchargement des dépendances.
5. Ouvrir `GestionProduitsApplication.java` et cliquer sur le triangle vert à côté de `main`.
6. Ouvrir http://localhost:8080/login

## Identifiants de démonstration
- Utilisateur : `oussama`
- Mot de passe : `Admin123!`

Changez ces identifiants avant toute utilisation réelle.

## Fonctionnalités
- Liste, ajout, modification et suppression de produits.
- Recherche par nom (insensible à la casse).
- Validation des champs.
- Connexion / déconnexion Spring Security.
- Quelques produits de démonstration chargés automatiquement.
- Test DAO / repository avec `@DataJpaTest`.

## Base H2
Par défaut, le projet utilise H2 en mémoire. Les données sont réinitialisées à chaque redémarrage.
Console H2 : http://localhost:8080/h2-console
JDBC URL : `jdbc:h2:mem:productsdb`
User : `sa`
Password : laisser vide.

## Utiliser MySQL à la place de H2
1. Créer une base de données MySQL :
   `CREATE DATABASE gestion_produits;`
2. Dans `src/main/resources/application.properties`, remplacer la configuration datasource H2 par :
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/gestion_produits
   spring.datasource.username=root
   spring.datasource.password=VOTRE_MOT_DE_PASSE
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Supprimer ou commenter les lignes H2 (`spring.h2.console...`) et remplacer :
   `spring.jpa.hibernate.ddl-auto=create-drop`
   par `spring.jpa.hibernate.ddl-auto=update`.
4. Lancer l'application.

## Lancer les tests
Dans le terminal IntelliJ :
`mvn test`

## Organisation
- `entity` : entité JPA Product
- `repository` : accès aux données Spring Data JPA
- `controller` : routes web MVC
- `security` : configuration Spring Security
- `templates` : pages Thymeleaf et layout
