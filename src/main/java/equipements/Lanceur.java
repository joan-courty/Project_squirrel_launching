package equipements;

public abstract class Lanceur {
    protected String nom;
    protected boolean peutEtreHabite;
    protected double carburantMax;
    protected int boostersMax;
    protected double chargeUtile; 
    protected double prix;

    public Lanceur(String nom, boolean peutEtreHabite, double carburantMax, int boostersMax, double chargeUtile, double prix) {
        this.nom = nom;
        this.peutEtreHabite = peutEtreHabite;
        this.carburantMax = carburantMax;
        this.boostersMax = boostersMax;
        this.chargeUtile = chargeUtile;
        this.prix = prix;
    }

    public abstract void afficherCaracteristiques();

    public String getNom() { return nom; }
    public boolean isPeutEtreHabite() { return peutEtreHabite; }
    public double getCarburantMax() { return carburantMax; }
    public int getBoostersMax() { return boostersMax; }
    public double getChargeUtile() { return chargeUtile; }
    public double getPrix() { return prix; }
}
