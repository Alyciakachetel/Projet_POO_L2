package Model;

import java.util.*;

public class Vente extends Observable {
    private Date date;
    private Client acheteur;
    private Employe vendeur;
    private Vector<LigneVente> ventesAssociees = new Vector<>();

    public Vente(Date d) {
        date = d;
    }
    
    public Date getDate() {
        return date;
    }

    public Client getAcheteur() {
        return acheteur;
    }

    public void setAcheteur(Client acheteur) {
        this.acheteur = acheteur;
        setChanged();
        notifyObservers();
    }

    public Employe getVendeur() {
        return vendeur;
    }

    public void setVendeur(Employe vendeur) {
        this.vendeur = vendeur;
        setChanged();
        notifyObservers();
    }

    public void ajouterLigneVente(LigneVente ligneVente) {
        ventesAssociees.add(ligneVente);
        setChanged();
        notifyObservers();
    }

    public float calculerMontantTotal() {
        float montantTotal = 0;
        for (LigneVente ligneVente : ventesAssociees) {
            montantTotal += ligneVente.calculerMontant();
        }
        return montantTotal;
    }

    public Vector<LigneVente> getLignesVente() {
        return ventesAssociees;
    }
}
