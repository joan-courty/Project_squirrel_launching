public abstract class Mission {
    protected String nom;
    protected boolean habitationRequise;
    protected double distanceKm;
    protected double coefficientCarburant;

    public Mission(String nom, boolean habitationRequise, double distanceKm, double coefficientCarburant) {
        this.nom = nom;
        this.habitationRequise = habitationRequise;
        this.distanceKm = distanceKm;
        this.coefficientCarburant = coefficientCarburant;
    }

    public abstract double calculerCarburantNecessaire(Fusee f);

    public String getNom() { return nom; }
    public boolean isHabitationRequise() { return habitationRequise; }
    public double getDistanceKm() { return distanceKm; }
}