public class Lune extends Mission {
    public Lune() {
        super("Lune", true, 400000.0, 0.005);
    }

    @Override
    public double calculerCarburantNecessaire(Fusee f) {
        return (f.getMasseTotale() * this.distanceKm * this.coefficientCarburant) / 1000.0;
    }
}