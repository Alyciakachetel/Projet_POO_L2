package Model;


public class LigneVente {

    private int quantite;
    private Vente ligne;
    private Produit produit;
    
    public LigneVente(int q, Vente v, Produit p) {
        quantite = q;
        ligne = v;
        produit = p;
    }
    
    public float calculerMontant() {
        return quantite * produit.getPrix();
    }

    public Vente getVente() {
        return ligne;
    }

    public Produit getProduit() {
        return produit;
    }

    public int getQuantite() { 
        return quantite;
    }
    
    public void setQuantite(int nouvelleQuantite) {
        quantite = nouvelleQuantite;
    }

    public String toString() {
        return "Quantité: " + quantite + ", Produit: " + produit.getModel() + ", Montant: " + calculerMontant();
    }


}

