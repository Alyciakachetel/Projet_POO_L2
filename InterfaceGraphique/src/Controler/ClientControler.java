package Controler;

import Model.*;
import Vue.FenetreClient;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientControler implements ActionListener {

    private FenetreClient fenetre;

    public ClientControler(FenetreClient fenetre) {
        this.fenetre = fenetre;
    }


    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();

        // Vérification du bouton cliqué
        if (source == fenetre.getBtnAjouter()) {
            // Récupération des informations du client
            String nom = fenetre.getTxtNom().getText();
            String adresse = fenetre.getTxtAdresse().getText();
            String numTel = fenetre.getTxtNumTel().getText();
            String email = fenetre.getTxtEmail().getText();

            // Vérification des champs vides
            if (nom.isEmpty() || adresse.isEmpty() || numTel.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(fenetre, "Veuillez remplir tous les champs.");
            } else if (!numTel.matches("\\d+")) {
                // Vérification du format du numéro de téléphone
                JOptionPane.showMessageDialog(fenetre, "Veuillez entrer un numéro de téléphone valide (uniquement des chiffres).");
            } else {
                // Vérification si un client avec le même nom existe déjà
                boolean clientExisteDeja = false;
                for (Client client : fenetre.getClients()) {
                    if (client.getNom().equalsIgnoreCase(nom)) {
                        clientExisteDeja = true;
                        break;
                    }
                }

                if (clientExisteDeja) {
                    // Affichage d'un message d'erreur si le client existe déjà
                    JOptionPane.showMessageDialog(fenetre, "Un client avec ce nom existe déjà.");
                } else {
                    // Ajout du nouveau client à la table
                    Client client = new Client(nom, adresse, numTel, email);
                    fenetre.addClientToTable(client);
                    // Réinitialisation des champs de texte
                    fenetre.getTxtNom().setText("");
                    fenetre.getTxtAdresse().setText("");
                    fenetre.getTxtNumTel().setText("");
                    fenetre.getTxtEmail().setText("");
                }
            }

        } else if (source == fenetre.getBtnSupprimer()) {
            // Suppression d'un client sélectionné
            int selectedRow = fenetre.getTable().getSelectedRow();
            if (selectedRow != -1) {
                fenetre.removeClientFromTable(selectedRow);
            } else {
                JOptionPane.showMessageDialog(fenetre, "Veuillez sélectionner un client à supprimer.");
            }
        }
    }
}