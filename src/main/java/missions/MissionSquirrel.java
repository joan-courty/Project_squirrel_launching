package missions;

import equipements.Fusee;

public class MissionSquirrel extends Mission {
    public MissionSquirrel() {
        super("Mission Squirrel", true, 1000000000000.0, 0.00000001); 
    }

    @Override
    public double calculerCarburantNecessaire(Fusee f) {
        return (f.getMasseTotale() * this.distanceKm * this.coefficientCarburant) / 1000.0;
    }
}
