package Model;

import java.util.*;

public class Magasin extends Observable {
    private String nom;
    private Vector<Produit> produits = new Vector<>();
    private Vector<Client> list_clients = new Vector<>();
    private Vector<Employe> list_employe = new Vector<>();

    public Magasin(String name) {
        nom = name;
    }

    public void ajoutClient(Client cl) {
        list_clients.add(cl);
        setChanged();
        notifyObservers();
    }

    public void ajoutProduit(Produit p) {
        produits.add(p);
        setChanged();
        notifyObservers();
    }

    public void suppProduit(Produit p) {
        produits.remove(p);
        setChanged();
        notifyObservers();
    }

    public void ajoutEmploye(Employe e) {
        list_employe.add(e);
        setChanged();
        notifyObservers();
    }

    public void suppEmploye(Employe e) {
        list_employe.remove(e);
        setChanged();
        notifyObservers();
    }

    public boolean searchClient(Client cl) {
        return list_clients.contains(cl);
    }

    public Vector<Client> getList_clients() {
        return list_clients;
    }

    public Vector<Produit> getProduits() {
        return produits;
    }

    public Vector<Employe> getList_employe() {
        return list_employe;
    }

}
