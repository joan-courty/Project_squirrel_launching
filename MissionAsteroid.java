public class MissionAsteroid extends Mission {
    public MissionAsteroid() {
        super("Minage Astéroïde", true, 3000000.0, 0.001); 
    }

    @Override
    public double calculerCarburantNecessaire(Fusee f) {
        return (f.getMasseTotale() * this.distanceKm * this.coefficientCarburant) / 1000.0;
    }
}