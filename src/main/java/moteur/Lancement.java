package moteur;
import equipements.Fusee;
import java.time.LocalDate;
import missions.Mission;

public class Lancement {
    private Fusee fusee;
    private Mission mission;
    private LocalDate dateLancement;
    private boolean estSucces;
    private String raison;
    private double coutTotal;

    // Constante des données
    private static final double PRIX_KEROSENE_PAR_TONNE = 1200.0;
    private static final double PROBABILITE_ECHEC_IMPREVU = 0.05;

    public Lancement(Fusee fusee, Mission mission) {
        this.fusee = fusee;
        this.mission = mission;
        this.dateLancement = LocalDate.now();
    }

    // Logique Simu
    public void simuler() {
        System.out.println("\n--- DÉBUT DE LA SIMULATION : " + mission.getNom() + " ---");
        
        try {
            double carburantRequis = mission.calculerCarburantNecessaire(fusee);
            System.out.println("Carburant requis calculé : " + carburantRequis + " t");

            // Vérification du carburant 
            if (carburantRequis > fusee.getLanceur().getCarburantMax()) {
                throw new CarburantInsuffisantException("Le carburant nécessaire (" + carburantRequis + "t) dépasse la capacité du lanceur (" + fusee.getLanceur().getCarburantMax() + "t).");
            }

            // Vérification de la surcharge
            if (fusee.getMasseTotale() > fusee.getLanceur().getChargeUtile()) {
                echouer("Surcharge dépassée (Masse: " + fusee.getMasseTotale() + "t > Charge utile: " + fusee.getLanceur().getChargeUtile() + "t)");
                return;
            }

            // Vérification des boosters
            if (fusee.getBoosters().size() > fusee.getLanceur().getBoostersMax()) {
                echouer("Trop de boosters (" + fusee.getBoosters().size() + " > " + fusee.getLanceur().getBoostersMax() + ")");
                return;
            }

            // Vérification de la compatibilité de l'équipage
            if (mission.isHabitationRequise() && !fusee.estHabitable()) {
                echouer("Capsule incompatible avec une mission habitée.");
                return;
            }

            // Tirage aléatoire
            double chance = Math.random();
            if (chance < PROBABILITE_ECHEC_IMPREVU) {
                echouer("Anomalie technique imprévue, pas de chance");
                return;
            }

            // Si toutes les conditions sont validées
            reussir(carburantRequis);

        } catch (CarburantInsuffisantException e) {
            echouer("Carburant insuffisant : " + e.getMessage());
        }
    }

    private void echouer(String motif) {
        this.estSucces = false;
        this.raison = motif;
        this.coutTotal = fusee.getPrixTotal(); // En cas d'échec, on perd le prix de la fusée
        System.out.println("ÉCHEC !!!!!!! - " + motif);
    }

    private void reussir(double carburantUtilise) {
        this.estSucces = true;
        this.raison = "Succès nominal";
        this.coutTotal = fusee.getPrixTotal() + (carburantUtilise * PRIX_KEROSENE_PAR_TONNE); // Cout = fusée + (carburant * prix)
        System.out.println("Réussi !!!!!!!!! L'équipage/cargo a atteint sa destination.");
        System.out.println("Coût total de l'opération : " + this.coutTotal + " €");
    }

    // Système de sauvegarde
    public String toTXT() {
        return dateLancement + ";" + fusee.getLanceur().getNom() + ";" + fusee.getCapsule().getNom() + ";" + mission.getNom() + ";" + (estSucces ? "SUCCES" : "ECHEC") + ";" + raison + ";" + coutTotal;
    }

    public boolean isSucces() { return estSucces; }
}
