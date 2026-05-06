package equipements;

public class Booster {
    private String nom;
    private int pousseeAdditionnelle; 
    private double masse; 
    private double prix; 

    public Booster(String nom, int pousseeAdditionnelle, double masse, double prix) {
        this.nom = nom;
        this.pousseeAdditionnelle = pousseeAdditionnelle;
        this.masse = masse;
        this.prix = prix;
    }

    public String getNom() { 
        return nom; 
    }

    public int getPousseeAdditionnelle() { 
        return pousseeAdditionnelle; 
    }

    public double getMasse() { 
        return masse; 
    }

    public double getPrix() { 
        return prix; 
    }
}
