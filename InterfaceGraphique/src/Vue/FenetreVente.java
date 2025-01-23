package Vue;

import Controler.VenteControler;
import Model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class FenetreVente extends JFrame implements Observer {
    private JComboBox<Client> cbClients;
    private JComboBox<Produit> cbProduits;
    private JComboBox<Employe> cbEmployes;
    private JTextField txtQuantite;
    private JButton btnAjouterProduit, btnEffectuerVente, btnAnnulerVente, btnConsulterVente;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private Vector<Produit> produits;
    private Vector<Client> clients;
    private Vector<Employe> employes;
    private Vector<Vente> ventes;
    private Vector<Produit> produitsSelectionnes;

    public FenetreVente(Vector<Client> clients, Vector<Produit> produits, Vector<Employe> employes, Vector<Vente> ventes) {
        super("Gestion des Ventes");
        this.clients = clients;
        this.produits = produits;
        this.employes = employes;
        this.ventes = ventes;
        this.produitsSelectionnes = new Vector<>();
        setSize(800, 500);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panneau de formulaire pour ajouter des produits à la vente
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        cbClients = new JComboBox<>(clients);
        cbProduits = new JComboBox<>(produits);
        cbEmployes = new JComboBox<>(employes);
        txtQuantite = new JTextField();
        btnAjouterProduit = new JButton("Ajouter Produit");
        btnEffectuerVente = new JButton("Effectuer Vente");
        btnAnnulerVente = new JButton("Annuler Vente");
        btnConsulterVente = new JButton("Consulter Vente");

        panelForm.add(new JLabel("Vendeur:"));
        panelForm.add(cbEmployes);
        panelForm.add(new JLabel("Client:"));
        panelForm.add(cbClients);
        panelForm.add(new JLabel("Produit:"));
        panelForm.add(cbProduits);
        panelForm.add(new JLabel("Quantité:"));
        panelForm.add(txtQuantite);

        add(panelForm, BorderLayout.NORTH);

        // Panneau de boutons pour les actions de vente
        JPanel panelBoutons = new JPanel(new GridLayout(1, 3, 10, 10));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        panelBoutons.add(btnAjouterProduit);
        panelBoutons.add(btnEffectuerVente);
        panelBoutons.add(btnAnnulerVente);
        panelBoutons.add(btnConsulterVente);
        
        add(panelBoutons, BorderLayout.SOUTH);

        // Tableau pour afficher les produits ajoutés à la vente
        model = new DefaultTableModel();
        model.addColumn("Produit");
        model.addColumn("Prix Unité");
        model.addColumn("Quantité");
        model.addColumn("Total");

        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        // Application de style
        StyleUtil.applyStyle(this);

        // Configuration des renderers pour les combo boxes
        cbClients.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Client) {
                    Client client = (Client) value;
                    setText(client.getNom());
                }
                return this;
            }
        });

        cbProduits.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Produit) {
                    Produit produit = (Produit) value;
                    setText(produit.getModel());
                }
                return this;
            }
        });

        cbEmployes.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Employe) {
                    Employe employe = (Employe) value;
                    setText(employe.getNom());
                }
                return this;
            }
        });

        // Configuration du contrôleur
        VenteControler controller = new VenteControler(this);
        btnAjouterProduit.addActionListener(controller);
        btnEffectuerVente.addActionListener(controller);
        btnAnnulerVente.addActionListener(controller);
        btnConsulterVente.addActionListener(controller);

        setVisible(true);
    }

    // Méthode appelée lorsque les données observées changent
    public void update(Observable o, Object arg) {
        cbClients.repaint();
        cbProduits.repaint();
        cbEmployes.repaint();
    }

    // Getters pour accéder aux composants de l'interface utilisateur
    public JComboBox<Client> getCbClients() { return cbClients; }
    public JComboBox<Produit> getCbProduits() { return cbProduits; }
    public JComboBox<Employe> getCbEmployes() { return cbEmployes; }
    public JTextField getTxtQuantite() { return txtQuantite; }
    public JButton getBtnAjouterProduit() { return btnAjouterProduit; }
    public JButton getBtnEffectuerVente() { return btnEffectuerVente; }
    public JButton getBtnAnnulerVente() { return btnAnnulerVente; }
    public JButton getBtnConsulterVente() { return btnConsulterVente; }
    public DefaultTableModel getModel() { return model; }
    public JTable getTable() { return table; }
    public Vector<Produit> getProduits() { return produits; }
    public Vector<Client> getClients() { return clients; }
    public Vector<Employe> getEmployes() { return employes; }
    public Vector<Produit> getProduitsSelectionnes() { return produitsSelectionnes; }
    public Vector<Vente> getVentes() { return ventes; }

    // Méthode pour ajouter une vente et l'observer
    public void addVente(Vente vente) {
        ventes.add(vente);
        vente.addObserver(this);
    }
}