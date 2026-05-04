public class Falcon9 extends Lanceur {
    public Falcon9() {
        super("Falcon 9", true, 500.0, 0, 22.0, 60.0);
    }

    @Override
    public void afficherCaracteristiques() {
        System.out.println(this.nom + " : Lanceur moderne, économique et certifié pour les vols habités.");
    }
}