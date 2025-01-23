package Controler;

import Vue.FenetreProduit;
import Model.Produit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProduitControler implements ActionListener {

    private FenetreProduit fenetre;

    public ProduitControler(FenetreProduit fenetre) {
        this.fenetre = fenetre;
    }

    
    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();
        if (source == fenetre.getBtnAjouter()) {
            // Récupération des informations du produit
            String model = fenetre.getTxtModel().getText().trim();
            String prixStr = fenetre.getTxtPrix().getText().trim();
            String quantiteStr = fenetre.getTxtQuantite().getText().trim();

            // Vérification des champs vides
            if (model.isEmpty() || prixStr.isEmpty() || quantiteStr.isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Veuillez remplir tous les champs pour ajouter un produit.");
                return;
            }

            try {
                float prix = Float.parseFloat(prixStr);
                int quantite = Integer.parseInt(quantiteStr);

                boolean found = false;
                // Vérification si le produit existe déjà
                for (Produit produit : fenetre.getProduits()) {
                    if (produit.getModel().equals(model) && produit.getPrix() == prix) {
                        produit.setQuantite(produit.getQuantite() + quantite);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    // Ajout du nouveau produit
                    Produit nouveauProduit = new Produit(model, prix, quantite);
                    fenetre.getProduits().add(nouveauProduit);
                }

                fenetre.updateTable();
                // Réinitialisation des champs de texte
                fenetre.getTxtModel().setText("");
                fenetre.getTxtPrix().setText("");
                fenetre.getTxtQuantite().setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(fenetre, "Veuillez entrer des valeurs numériques valides pour le prix et la quantité.");
            }
        } else if (source == fenetre.getBtnSupprimer()) {
            // Suppression d'un produit sélectionné
            int selectedRow = fenetre.getTable().getSelectedRow();
            String quantiteASupprimerStr = fenetre.getTxtQuantiteASupprimer().getText().trim();

            // Vérification de la quantité à supprimer
            if (selectedRow >= 0 && !quantiteASupprimerStr.isEmpty()) {
                try {
                    int quantiteASupprimer = Integer.parseInt(quantiteASupprimerStr);
                    Produit produit = fenetre.getProduits().get(selectedRow);
                    int nouvelleQuantite = produit.getQuantite() - quantiteASupprimer;

                    if (nouvelleQuantite > 0) {
                        produit.setQuantite(nouvelleQuantite);
                    } else {
                        fenetre.getProduits().remove(selectedRow);
                    }
                    fenetre.updateTable();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(fenetre, "Veuillez entrer un nombre valide pour la quantité à supprimer.");
                }

            } else {
                JOptionPane.showMessageDialog(fenetre, "Veuillez sélectionner un produit et indiquer la quantité à supprimer.");
            }
        }
    }
}