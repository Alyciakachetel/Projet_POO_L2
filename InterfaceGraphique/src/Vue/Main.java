package Vue;

import Model.*;
import Controler.*;

public class Main {

    public static void main(String[] args) {
        // Création du magasin
        Magasin magasin = new Magasin("Mon Magasin");

        // Création de deux produits
        Produit produit1 = new Ordinateur("MacBook Pro", 2200.00f, 100);
        Produit produit2 = new Telephone("iPhone 15 PRO MAX", 1800.00f, 200);
        Produit produit3 = new Ordinateur("iMac", 4200.00f, 100);
        Produit produit4 = new Telephone("iPhone 14 PRO MAX", 1200.00f, 200);
        Produit produit5 = new Ordinateur("MacBook Air", 1500.00f, 150);
        Produit produit6 = new Telephone("iPhone 13", 900.00f, 250);
        Produit produit7 = new Ordinateur("Dell XPS", 1800.00f, 100);
        Produit produit8 = new Telephone("iPhone 12", 700.00f, 300);
        Produit produit9 = new Ordinateur("Asus ZenBook", 1600.00f, 120);
        Produit produit10 = new Telephone("iPhone 11", 500.00f, 300);

        // Ajout des produits au magasin
        magasin.ajoutProduit(produit1);
        magasin.ajoutProduit(produit2);
        magasin.ajoutProduit(produit3);
        magasin.ajoutProduit(produit4);
        magasin.ajoutProduit(produit5);
        magasin.ajoutProduit(produit6);
        magasin.ajoutProduit(produit7);
        magasin.ajoutProduit(produit8);
        magasin.ajoutProduit(produit9);
        magasin.ajoutProduit(produit10);
        
        // Création de deux clients
        Client client1 = new Client("Abdelmalek", "45 Rue des Orteaux, Paris", "0602529383", "abdelmalek2326@gmail.com");
        Client client2 = new Client("Alyce", "1 impasse de la presdecelle, Evry", "0749502641", "alyciakachetel@gmail.com");

        // Ajout des clients au magasin
        magasin.ajoutClient(client1);
        magasin.ajoutClient(client2);
        
     // Création de deux employés
        Employe employe1 = new Employe("Tarek", "Vendeur");
        Employe employe2 = new Employe("Messi", "Vendeur");

        // Ajout des employés au magasin
        magasin.ajoutEmploye(employe1);
        magasin.ajoutEmploye(employe2);

        // Création de la fenêtre principale avec le magasin
        FenetreMagasin fenetreMagasin = new FenetreMagasin(magasin);
        new MagasinControler(fenetreMagasin, magasin);

        fenetreMagasin.setVisible(true);
    }
}