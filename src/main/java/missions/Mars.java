package missions;

import equipements.Fusee;
public class Mars extends Mission {
    public Mars() {
        super("Mars", true, 225000000.0, 0.000015);
    }

    @Override
    public double calculerCarburantNecessaire(Fusee f) {
        return (f.getMasseTotale() * this.distanceKm * this.coefficientCarburant) / 1000.0;
    }
}
