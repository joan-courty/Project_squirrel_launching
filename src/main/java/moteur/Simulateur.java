package moteur;

import equipements.*;
import java.io.*;
import java.util.*;
import missions.*;

public class Simulateur {
    private static Simulateur instance = null;

    private List<Lanceur> catalogueLanceurs;
    private List<Capsule> catalogueCapsules;
    private List<Booster> catalogueBoosters;
    private List<Mission> catalogueMissions;

    private Fusee fuseeActuelle;
    private Mission missionActuelle;
    private List<String> historique;

    private final String FICHIER_HISTORIQUE = "historique.txt";

    private Simulateur() {
        historique = new ArrayList<>();
        initialiserCatalogues();
        chargerHistorique();
    }

    public static Simulateur getInstance() {
        if (instance == null) {
            instance = new Simulateur();
        }
        return instance;
    }

    private void initialiserCatalogues() {
        catalogueLanceurs = Arrays.asList(new Ariane5(), new SaturneV(), new Falcon9());
        catalogueCapsules = Arrays.asList(new Orion(), new CargoDragon());
        catalogueBoosters = Arrays.asList(
            new Booster("EAP", 6470, 270.0, 30.0),
            new Booster("SRB", 12500, 590.0, 55.0),
            new Booster("BE-3", 490, 25.0, 12.0)
        );
        catalogueMissions = Arrays.asList(
            new OrbiteTerrestre(), new ISS(), new Lune(), new Mars(), new MissionSquirrel()
        );
    }

    private void chargerHistorique() {
        File fichier = new File(FICHIER_HISTORIQUE);
        if (!fichier.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                historique.add(ligne);
            }
            System.out.println("Historique chargé : " + historique.size() + " lancements précédents.");
        } catch (IOException e) {
            System.out.println("Erreur de lecture de l'historique : " + e.getMessage());
        }
    }

    public void sauvegarderResultat(Lancement lancement) {
        String txt = lancement.toTXT();
        historique.add(txt); 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER_HISTORIQUE, true))) {
            bw.write(txt);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erreur d'écriture dans l'historique : " + e.getMessage());
        }
    }

    public void demarrer() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {

            //ClearConsole.clear(); 

            System.out.println("\n===========================================");
            System.out.println("             PROJECT SQUIRREL              ");
            System.out.println("===========================================");
            System.out.println("1. Configurer une fusée sur mesure");
            System.out.println("2. Choisir une mission");
            System.out.println("3. Lancer la simulation");
            System.out.println("4. Afficher l'historique");
            System.out.println("5. Quitter");
            System.out.print("\nVotre choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    configurerFuseeInteractive(scanner);
                    break;
                case "2":
                    choisirMissionInteractive(scanner);
                    break;
                case "3":
                    executerLancement();
                    break;
                case "4":
                    afficherHistorique();
                    break;
                case "5":
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez taper un chiffre entre 1 et 5.");
            }
        }
        scanner.close();
        System.out.println("Vous êtes parti, à bientôt.");
    }

    private int lireEntierSecurise(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("Votre choix (" + min + "-" + max + ") : ");
            try {
                int choix = Integer.parseInt(scanner.nextLine().trim());
                if (choix >= min && choix <= max) return choix;
                System.out.println("Erreur : choix hors limites.");
            } catch (NumberFormatException e) {
                System.out.println("Erreur : veuillez saisir un nombre valide.");
            }
        }
    }

    private void configurerFuseeInteractive(Scanner scanner) {
        System.out.println("\n--- CHOIX DU LANCEUR ---");
        for (int i = 0; i < catalogueLanceurs.size(); i++) {
            System.out.println((i + 1) + ". " + catalogueLanceurs.get(i).getNom());
        }
        Lanceur l = catalogueLanceurs.get(lireEntierSecurise(scanner, 1, catalogueLanceurs.size()) - 1);

        System.out.println("\n--- CHOIX DE LA CAPSULE ---");
        for (int i = 0; i < catalogueCapsules.size(); i++) {
            System.out.println((i + 1) + ". " + catalogueCapsules.get(i).getNom());
        }
        Capsule c = catalogueCapsules.get(lireEntierSecurise(scanner, 1, catalogueCapsules.size()) - 1);

        this.fuseeActuelle = new Fusee(l, c);

        int maxB = l.getBoostersMax();
        System.out.println("\nLe lanceur " + l.getNom() + " peut recevoir " + maxB + " boosters.");
        
        while (this.fuseeActuelle.getBoosters().size() < maxB) {
            System.out.print("Ajouter un booster ? (o/n) : ");
            String rep = scanner.nextLine().trim().toLowerCase();
            if (!rep.equals("o")) break;

            System.out.println("Voici les différents boosters :");
            for (int i = 0; i < catalogueBoosters.size(); i++) {
                System.out.println((i + 1) + ". " + catalogueBoosters.get(i).getNom());
            }
            this.fuseeActuelle.ajouterBooster(catalogueBoosters.get(lireEntierSecurise(scanner, 1, catalogueBoosters.size()) - 1));
        }
        System.out.println("\n Fusée prête à décollé !");
    }

    private void choisirMissionInteractive(Scanner scanner) {
        System.out.println("\n--- MISSIONS DISPONIBLES ---");
        for (int i = 0; i < catalogueMissions.size(); i++) {
            System.out.println((i + 1) + ". " + catalogueMissions.get(i).getNom());
        }
        this.missionActuelle = catalogueMissions.get(lireEntierSecurise(scanner, 1, catalogueMissions.size()) - 1);
        System.out.println("Vous avez choisi la mission : " + this.missionActuelle.getNom());
    }

    private void executerLancement() {
        if (fuseeActuelle == null || missionActuelle == null) {
            System.out.println("Erreur : Configurez d'abord la fusée et la mission avant de partir.");
            return;
        }
        Lancement lancement = new Lancement(fuseeActuelle, missionActuelle);
        lancement.simuler();
        sauvegarderResultat(lancement);
    }

    private void afficherHistorique() {
        System.out.println("\n--- HISTORIQUE ---");
        if (historique.isEmpty()) System.out.println("Aucune donnée.");
        else for (String s : historique) System.out.println(s);
    }
}