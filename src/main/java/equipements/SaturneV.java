package equipements;

public class SaturneV extends Lanceur {
    public SaturneV() {
        super("Saturne V", true, 2700.0, 0, 140.0, 1500.0);
    }

    @Override
    public void afficherCaracteristiques() {
        System.out.println(this.nom + " : Le lanceur mythique des missions Apollo, avec une immense capacité de charge.");
    }
}
