package Vue;

import Controler.ProduitControler;
import Model.Produit;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class FenetreProduit extends JFrame implements Observer {
    private JTextField txtModel, txtPrix, txtQuantite, txtQuantiteASupprimer;
    private JButton btnAjouter, btnSupprimer;
    private JTable table;
    private DefaultTableModel model;
    private Vector<Produit> produits;

    public FenetreProduit(Vector<Produit> produits) {
        super("Gestion Produit");
        this.produits = produits;
        setSize(800, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panneau de formulaire pour ajouter et supprimer des produits
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtModel = new JTextField();
        txtPrix = new JTextField();
        txtQuantite = new JTextField();
        txtQuantiteASupprimer = new JTextField();
        btnAjouter = new JButton("Ajouter");
        btnSupprimer = new JButton("Supprimer");

        panelForm.add(new JLabel("Modèle :"));
        panelForm.add(txtModel);
        panelForm.add(new JLabel("Prix Unité :"));
        panelForm.add(txtPrix);
        panelForm.add(new JLabel("Quantité à ajouter :"));
        panelForm.add(txtQuantite);
        panelForm.add(new JLabel("Quantité à supprimer :"));
        panelForm.add(txtQuantiteASupprimer);
        panelForm.add(btnAjouter);
        panelForm.add(btnSupprimer);

        add(panelForm, BorderLayout.WEST);

        // Tableau pour afficher les produits
        model = new DefaultTableModel();
        model.addColumn("Modèle");
        model.addColumn("Prix Unité");
        model.addColumn("Quantité");
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        updateTable();
        
        // Application de style
        StyleUtil.applyStyle(this);
        
        // Configuration du contrôleur
        ProduitControler controller = new ProduitControler(this);
        btnAjouter.addActionListener(controller);
        btnSupprimer.addActionListener(controller);

        setVisible(true);
    }

    // Méthode pour mettre à jour le tableau avec les produits existants
    public void updateTable() {
        model.setRowCount(0); // Efface les données existantes
        for (Produit p : produits) {
            model.addRow(new Object[]{p.getModel(), p.getPrix(), p.getQuantite()});
            p.addObserver(this);
        }
    }

    // Méthode appelée lorsque les données observées changent
    public void update(Observable o, Object arg) {
        updateTable();
    }

    // Getters pour accéder aux composants de l'interface utilisateur
    public JTextField getTxtModel() { return txtModel; }
    public JTextField getTxtPrix() { return txtPrix; }
    public JTextField getTxtQuantite() { return txtQuantite; }
    public JTextField getTxtQuantiteASupprimer() { return txtQuantiteASupprimer; }
    public Vector<Produit> getProduits() { return produits; }
    public DefaultTableModel getModel() { return model; }
    public JButton getBtnAjouter() { return btnAjouter; }
    public JButton getBtnSupprimer() { return btnSupprimer; }
    public JTable getTable() { return table; }
}