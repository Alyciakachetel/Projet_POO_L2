package Controler;


import Model.*;
import Vue.FenetreVente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class VenteControler implements ActionListener {
	
    private FenetreVente fenetre;

    public VenteControler(FenetreVente fenetre) {
        this.fenetre = fenetre;
    }

    
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        
        // Action pour ajouter un produit à la vente
        if (source == fenetre.getBtnAjouterProduit()) {
            try {
                int quantite = Integer.parseInt(fenetre.getTxtQuantite().getText());
                if (quantite <= 0) {
                    JOptionPane.showMessageDialog(fenetre, "Veuillez entrer une quantité positive.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Produit produit = (Produit) fenetre.getCbProduits().getSelectedItem();
                if (produit != null) {
                	// Vérifier si la quantité demandée dépasse la quantité en stock
                    int totalQuantiteDemande = quantite;
                    for (Produit p : fenetre.getProduitsSelectionnes()) {
                        if (p.getModel().equals(produit.getModel())) {
                            totalQuantiteDemande += p.getQuantite();
                        }
                    }

                    if (totalQuantiteDemande > produit.getQuantite()) {
                        JOptionPane.showMessageDialog(fenetre, "Quantité demandée supérieure à la quantité en stock.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    fenetre.getProduitsSelectionnes().add(new Produit(produit.getModel(), produit.getPrix(), quantite));
                    fenetre.getModel().addRow(new Object[]{produit.getModel(), produit.getPrix(), quantite, quantite * produit.getPrix()});
                    fenetre.getCbClients().setEnabled(false);
                    fenetre.getCbEmployes().setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(fenetre, "Veuillez entrer une valeur numérique valide pour la quantité.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            }
        } 
     // Action pour effectuer la vente
        else if (source == fenetre.getBtnEffectuerVente()) {
            if (fenetre.getProduitsSelectionnes().isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Aucun produit ajouté à la vente. Veuillez ajouter des produits avant de continuer.", "Erreur de vente", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Vérifier si la quantité demandée dépasse la quantité en stock avant de finaliser la vente
            Map<String, Integer> quantiteDemandeeParProduit = new HashMap<>();
            for (Produit p : fenetre.getProduitsSelectionnes()) {
                quantiteDemandeeParProduit.put(p.getModel(), quantiteDemandeeParProduit.getOrDefault(p.getModel(), 0) + p.getQuantite());
            }

            for (Map.Entry<String, Integer> entry : quantiteDemandeeParProduit.entrySet()) {
                for (Produit originalProduct : fenetre.getProduits()) {
                    if (originalProduct.getModel().equals(entry.getKey()) && entry.getValue() > originalProduct.getQuantite()) {
                        JOptionPane.showMessageDialog(fenetre, "Quantité demandée pour " + entry.getKey() + " dépasse la quantité en stock.", "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            Client client = (Client) fenetre.getCbClients().getSelectedItem();
            Employe employe = (Employe) fenetre.getCbEmployes().getSelectedItem();
            Vente vente = new Vente(new Date());
            vente.setAcheteur(client);
            vente.setVendeur(employe);

            for (Produit p : fenetre.getProduitsSelectionnes()) {
                vente.ajouterLigneVente(new LigneVente(p.getQuantite(), vente, p));
             // Mettre à jour la quantité dans le produit original et notifier les observateurs
                for (Produit originalProduct : fenetre.getProduits()) {
                    if (originalProduct.getModel().equals(p.getModel())) {
                        originalProduct.setQuantite(originalProduct.getQuantite() - p.getQuantite());
                        break;
                    }
                }
            }

            fenetre.addVente(vente);// Ajouter la vente à la liste des ventes
            JOptionPane.showMessageDialog(fenetre, "Vente effectuée avec succès!");

            // Clear the table and reset products selection
            fenetre.getModel().setRowCount(0);
            fenetre.getProduitsSelectionnes().clear();
            fenetre.getCbClients().setEnabled(true); // Réactiver la sélection du client
            fenetre.getCbEmployes().setEnabled(true); // Réactiver la sélection de l'employé
            
        } 
        // Action pour annuler la vente
        else if (source == fenetre.getBtnAnnulerVente()) {
            if (fenetre.getProduitsSelectionnes().isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Aucun produit à annuler. Veuillez ajouter des produits avant de continuer.", "Erreur d'annulation", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Effacer le tableau et réinitialiser la sélection des produits
            fenetre.getModel().setRowCount(0);
            fenetre.getProduitsSelectionnes().clear();
            fenetre.getCbClients().setEnabled(true);
            fenetre.getCbEmployes().setEnabled(true);
            JOptionPane.showMessageDialog(fenetre, "Vente annulée.");
            
        }else if (source == fenetre.getBtnConsulterVente()) {
        	
        	// Afficher les données des ventes
            StringBuilder salesData = new StringBuilder("Historique des ventes:\n");
            float montantTotal = 0;
            
            for (Vente vente : fenetre.getVentes()) {
                salesData.append("Date: ").append(vente.getDate()).append(", Vendeur: ").append(vente.getVendeur().getNom()).append(", Client: ").append(vente.getAcheteur().getNom()).append("\n");
                for (LigneVente ligne : vente.getLignesVente()) {
                    salesData.append("  Produit: ").append(ligne.getProduit().getModel())
                            .append(", Quantité: ").append(ligne.getQuantite())
                            .append(", Total: ").append(ligne.calculerMontant()).append("\n");
                    montantTotal += ligne.calculerMontant();
                }
                salesData.append("Montant total pour ").append(vente.getAcheteur().getNom()).append(": ").append(montantTotal).append("\n\n");
                montantTotal = 0;  // Réinitialiser montantTotal pour le client suivant
            }
            JOptionPane.showMessageDialog(fenetre, salesData.toString(), "Historique des Ventes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}