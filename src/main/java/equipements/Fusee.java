package equipements;

import java.util.ArrayList;
import java.util.List;

public class Fusee {
    // Composition fusee
    private Lanceur lanceur;
    private Capsule capsule;
    private List<Booster> boosters;

    // Constructeur
    public Fusee(Lanceur lanceur, Capsule capsule) {
        this.lanceur = lanceur;
        this.capsule = capsule;
        this.boosters = new ArrayList<>(); // Liste vide
    }

    public boolean ajouterBooster(Booster b) {
        if (this.boosters.size() < this.lanceur.getBoostersMax()) {
            this.boosters.add(b);
            System.out.println("Booster " + b.getNom() + " ajouté avec succès.");
            return true;
        } else {
            System.out.println("Impossible d'ajouter le booster : la limite de " + this.lanceur.getBoostersMax() + " pour ce lanceur est atteinte.");
            return false;
        }
    }

    public double getPrixTotal() {
        double prixTotal = lanceur.getPrix() + capsule.getPrix();
        for (Booster b : boosters) {
            prixTotal += b.getPrix();
        }
        return prixTotal;
    }

    public double getMasseTotale() {
        double masseTotale = capsule.getMasse();
        for (Booster b : boosters) {
            masseTotale += b.getMasse();
        }
        return masseTotale;
    }

    public boolean estHabitable() {
        return lanceur.isPeutEtreHabite() && capsule.isHabitee();
    }

    public Lanceur getLanceur() { return lanceur; }
    public Capsule getCapsule() { return capsule; }
    public List<Booster> getBoosters() { return boosters; }
    
    public void afficherConfiguration() {
        System.out.println("--- CONFIGURATION DE LA FUSÉE ---");
        System.out.println("Lanceur : " + lanceur.getNom());
        System.out.println("Capsule : " + capsule.getNom() + " (Habitable : " + estHabitable() + ")");
        System.out.println("Nombre de boosters : " + boosters.size() + " / " + lanceur.getBoostersMax());
        System.out.println("Prix total de la configuration : " + getPrixTotal() + " M€");
        System.out.println("----------------------------------");
    }
}
