package Controler;

import Vue.*;
import Model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MagasinControler implements ActionListener {

    private FenetreMagasin fenetre;
    private Magasin magasin;
    private Vector<Vente> ventes;

    public MagasinControler(FenetreMagasin fenetre, Magasin magasin) {
        this.fenetre = fenetre;
        this.magasin = magasin;
        this.ventes = new Vector<>();

        // Ajout des listeners aux boutons
        fenetre.getBtnProduit().addActionListener(this);
        fenetre.getBtnClient().addActionListener(this);
        fenetre.getBtnVente().addActionListener(this);
        fenetre.getBtnStatistique().addActionListener(this);
    }

    
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String btnText = source.getText();
        switch (btnText) {
            case "Gestion Produit":
                // Ouverture de la fenêtre de gestion des produits
                FenetreProduit fenetreProduit = new FenetreProduit(magasin.getProduits());
                magasin.addObserver(fenetreProduit);
                fenetreProduit.setVisible(true);
                break;
            case "Gestion Client":
                // Ouverture de la fenêtre de gestion des clients
                FenetreClient fenetreClient = new FenetreClient(magasin.getList_clients());
                magasin.addObserver(fenetreClient);
                fenetreClient.setVisible(true);
                break;
            case "Gestion Vente":
                // Ouverture de la fenêtre de gestion des ventes
                FenetreVente fenetreVente = new FenetreVente(magasin.getList_clients(), magasin.getProduits(), magasin.getList_employe(), ventes);
                magasin.addObserver(fenetreVente);
                fenetreVente.setVisible(true);
                break;
            case "Statistique":
                // Ouverture de la fenêtre de statistiques
                new FenetreStatistique(ventes).setVisible(true);
                break;
            default:
                break;
        }
    }
}