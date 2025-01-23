package Controler;

import Vue.FenetreStatistique;
import Model.Vente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class StatistiqueControler implements ActionListener {

    private FenetreStatistique fenetre;
    private Vector<Vente> ventes;

    public StatistiqueControler(FenetreStatistique fenetre, Vector<Vente> ventes) {
        this.fenetre = fenetre;
        this.ventes = ventes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Vérification de l'option sélectionnée
        if (fenetre.getRbParArticle().isSelected()) {
            afficherStatistiquesParArticle();
        } else if (fenetre.getRbParClient().isSelected()) {
            afficherStatistiquesParClient();
        } else {
            JOptionPane.showMessageDialog(fenetre, "Veuillez sélectionner une option.");
        }
    }

    public void afficherStatistiquesParArticle() {
        // Mise à jour du modèle de tableau
        fenetre.getModel().setRowCount(0);
        fenetre.getModel().setColumnIdentifiers(new Object[]{"Article", "Quantité Vendue", "Montant Total"});

        // Création d'une carte pour stocker les statistiques par article
        Map<String, int[]> statsParArticle = new HashMap<>();
        
        // Parcours de toutes les ventes
        for (Vente vente : ventes) {
            // Parcours de toutes les lignes de vente pour chaque vente
            for (Model.LigneVente ligne : vente.getLignesVente()) {
                String article = ligne.getProduit().getModel();
                int quantite = ligne.getQuantite();
                float montant = ligne.calculerMontant();

                // Si l'article n'est pas encore dans la carte, l'ajouter
                if (!statsParArticle.containsKey(article)) {
                    statsParArticle.put(article, new int[]{0, 0});
                }
                // Mise à jour des quantités et des montants pour chaque article
                statsParArticle.get(article)[0] += quantite;
                statsParArticle.get(article)[1] += montant;
            }
        }

        // Ajout des statistiques au modèle de tableau
        for (Map.Entry<String, int[]> entry : statsParArticle.entrySet()) {
            String article = entry.getKey();
            int quantiteVendue = entry.getValue()[0];
            int montantTotal = entry.getValue()[1];
            fenetre.getModel().addRow(new Object[]{article, quantiteVendue, montantTotal});
        }
    }

    public void afficherStatistiquesParClient() {
        // Mise à jour du modèle de tableau
        fenetre.getModel().setRowCount(0);
        fenetre.getModel().setColumnIdentifiers(new Object[]{"Client", "Quantité Achetee", "Montant Total"});

        // Création d'une carte pour stocker les statistiques par client
        Map<String, int[]> statsParClient = new HashMap<>();
        
        // Parcours de toutes les ventes
        for (Vente vente : ventes) {
            String client = vente.getAcheteur().getNom();
            int quantiteTotale = 0;
            float montantTotal = 0;

            // Parcours de toutes les lignes de vente pour chaque vente
            for (Model.LigneVente ligne : vente.getLignesVente()) {
                quantiteTotale += ligne.getQuantite();
                montantTotal += ligne.calculerMontant();
            }

            // Si le client n'est pas encore dans la carte, l'ajouter
            if (!statsParClient.containsKey(client)) {
                statsParClient.put(client, new int[]{0, 0});
            }
            // Mise à jour des quantités et des montants pour chaque client
            statsParClient.get(client)[0] += quantiteTotale;
            statsParClient.get(client)[1] += montantTotal;
        }

        // Ajout des statistiques au modèle de tableau
        for (Map.Entry<String, int[]> entry : statsParClient.entrySet()) {
            String client = entry.getKey();
            int quantiteAchetee = entry.getValue()[0];
            int montantTotal = entry.getValue()[1];
            fenetre.getModel().addRow(new Object[]{client, quantiteAchetee, montantTotal});
        }
    }
}