package Vue;

import Controler.StatistiqueControler;
import Model.Vente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class FenetreStatistique extends JFrame implements Observer {
    private JRadioButton rbParArticle, rbParClient;
    private ButtonGroup bgOptions;
    private JButton btnAfficher;
    private JTable table;
    private DefaultTableModel model;

    private Vector<Vente> ventes;

    public FenetreStatistique(Vector<Vente> ventes) {
        super("Statistiques de Vente");
        this.ventes = ventes;
        setSize(800, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panneau d'options pour choisir les statistiques à afficher
        JPanel panelOptions = new JPanel(new FlowLayout());
        rbParArticle = new JRadioButton("Par Article");
        rbParClient = new JRadioButton("Par Client");
        bgOptions = new ButtonGroup();
        bgOptions.add(rbParArticle);
        bgOptions.add(rbParClient);
        btnAfficher = new JButton("Afficher");

        panelOptions.add(rbParArticle);
        panelOptions.add(rbParClient);
        panelOptions.add(btnAfficher);

        add(panelOptions, BorderLayout.NORTH);

        // Tableau pour afficher les statistiques
        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Application de style
        StyleUtil.applyStyle(this);

        // Configuration du contrôleur
        StatistiqueControler controller = new StatistiqueControler(this, ventes);
        btnAfficher.addActionListener(controller);

        setVisible(true);
    }

    // Méthode appelée lorsque les données observées changent
    public void update(Observable o, Object arg) {
        // Mise à jour de l'interface utilisateur en fonction des changements de données observées
        btnAfficher.doClick(); // Simule un clic sur le bouton "Afficher" pour rafraîchir le tableau
    }

    // Getters pour accéder aux composants de l'interface utilisateur
    public JRadioButton getRbParArticle() { return rbParArticle; }
    public JRadioButton getRbParClient() { return rbParClient; }
    public DefaultTableModel getModel() { return model; }
    public JButton getBtnAfficher() { return btnAfficher; }
    public Vector<Vente> getVentes() { return ventes; }
}