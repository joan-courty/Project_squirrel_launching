package missions;

import equipements.Fusee;
public class OrbiteTerrestre extends Mission {
    public OrbiteTerrestre() {
        super("Orbite terrestre", false, 400.0, 1.0);
    }

    @Override
    public double calculerCarburantNecessaire(Fusee f) {
        // Formule: (masse totale * distance * coeff) / 1000
        return (f.getMasseTotale() * this.distanceKm * this.coefficientCarburant) / 1000.0;
    }
}
