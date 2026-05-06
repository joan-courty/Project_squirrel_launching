package equipements;

public abstract class Capsule {
    protected String nom;
    protected boolean estHabitee;
    protected int occupantsMax;
    protected double masse;
    protected double prix;

    public Capsule(String nom, boolean estHabitee, int occupantsMax, double masse, double prix) {
        this.nom = nom;
        this.estHabitee = estHabitee;
        this.occupantsMax = occupantsMax;
        this.masse = masse;
        this.prix = prix;
    }
    
    public String getNom() {
        return nom;
    }

    public boolean isHabitee() {
        return estHabitee;
    }

    public int getOccupantsMax() {
        return occupantsMax;
    }

    public double getMasse() {
        return masse;
    }

    public double getPrix() {
        return prix;
    }
}
