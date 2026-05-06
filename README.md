# Simulateur-de-lancement-spatial-

## Exécution

Lancez le simulateur via la classe Main située dans le package moteur :

```bash
cd src/main/java
java moteur.Main
```

## Architecture Technique (POO)

Le projet met en œuvre les concepts fondamentaux de la Programmation Orientée Objet demandés :

### Héritage & Abstraction
Utilisation de classes abstraites pour Lanceur, Capsule et Mission. Chaque catégorie possède au moins 3 implémentations concrètes (ex: SaturneV, Ariane5, Falcon9 pour les lanceurs).

### Composition
La classe Fusee est le pivot du projet. Elle n'hérite d'aucune classe mais est composée d'un objet Lanceur, d'un objet Capsule et d'une List<Booster>.

### Polymorphisme
Les calculs de carburant sont effectués via un appel polymorphique à la méthode calculerCarburantNecessaire() définie dans la classe Mission et redéfinie dans chaque sous-classe.

### Encapsulation
Tous les attributs sont privés et accessibles via des getters/setters lorsque cela est justifié par la logique métier.

### Gestion des Exceptions
Implémentation d'une exception personnalisée CarburantInsuffisantException pour gérer les erreurs de configuration avant le lancement.

## Focus : Mission Personnelle (MissionSquirrel)

Conformément au barème du TP, une cinquième mission originale a été intégrée : MissionSquirrel, ce nom de Mission m'est venu à l'idée lorque Louis a dit que l'on pouvait appelé le projet comme l'on voulait.

### Concept
Une mission plus ou moins secrete qui mène à la planête écureuil.

### Spécificités

- **Distance** : 1 000 000 000 000 km.
- **Logique** : Elle impose une vérification stricte de la charge utile et du type de capsule utilisé (le ravitaillement nécessite une capsule de type Cargo ou Habitée avec un inventaire spécifique).
- **Justification POO** : Cette classe démontre la capacité de l'architecture à s'étendre facilement : il suffit d'hériter de Mission et de définir le nouveau coefficient de carburant sans modifier le code existant du Simulateur.

## Persistance & UX

### Historique
Les résultats sont enregistrés dans un fichier historique.txt. Les données sont rechargées dynamiquement à chaque lancement de l'application.

### Interface
Un menu console robuste avec gestion des entrées invalides (via Scanner et try-catch) pour éviter tout crash du programme.

## Déclaration d'utilisation d'IA

Dans le cadre de ce projet, l'IA (Gemini) a été utilisée comme outil pour :

- **Conception de l'architecture** : Aide à la mise en place de la structure par packages pour respecter les conventions Java.
- **Résolution de bugs** : Aide au débogage des erreurs de chemin de classe (ClassNotFoundException) liées à l'exécution dans le terminal.
- **Review** : Review entière du code afin d'être sure que je n'ai rien manqué.