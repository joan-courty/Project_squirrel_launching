public class Ariane5 extends Lanceur {
    public Ariane5() {
        super("Ariane 5", false, 700.0, 2, 20.0, 180.0);
    }

    @Override
    public void afficherCaracteristiques() {
        System.out.println(this.nom + " : est idéal pour les charges lourdes non habitées.");
    }
}