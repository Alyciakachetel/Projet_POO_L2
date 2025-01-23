package Model;

import java.util.*;

public class Produit extends Observable {
    protected String model;
    protected float prix_unit;
    protected int quantite;  
    protected Magasin magasin;
    protected Vector<LigneVente> lignes = new Vector<>();

    public Produit(String m, float p, int q) {  
        model = m;
        prix_unit = p;
        quantite = q;
    }

    public String getModel() {
        return model;
    }

    public float getPrix() {
        return prix_unit;
    }

    public int getQuantite() { 
        return quantite;
    }

    public void setQuantite(int quantite) {  
        this.quantite = quantite;
    }

    public void ajouterLigneVente(LigneVente ligneVente) {
        lignes.add(ligneVente);
    }

    public Vector<LigneVente> getLignesVente() {
        return lignes;
    }
    
    public String toString() {
        return model;
    }
}