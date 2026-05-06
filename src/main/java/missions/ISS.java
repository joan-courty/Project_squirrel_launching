package missions;

import equipements.Fusee;
public class ISS extends Mission {
    public ISS() {
        super("ISS", true, 400.0, 1.2); 
    }

    @Override
    public double calculerCarburantNecessaire(Fusee f) {
        return (f.getMasseTotale() * this.distanceKm * this.coefficientCarburant) / 1000.0;
    }
}
