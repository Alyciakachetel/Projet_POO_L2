package Vue;

import Model.Client;
import Controler.ClientControler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class FenetreClient extends JFrame implements Observer {
    private JTextField txtNom, txtAdresse, txtNumTel, txtEmail;
    private JButton btnAjouter, btnSupprimer;
    public JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private Vector<Client> clients;

    public FenetreClient(Vector<Client> clients) {
        super("Gestion Client");
        this.clients = clients;
        setSize(800, 500);
        setLayout(new BorderLayout());

        // Panneau de formulaire pour ajouter des clients
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 5, 5));
        txtNom = new JTextField();
        txtAdresse = new JTextField();
        txtNumTel = new JTextField();
        txtEmail = new JTextField();
        btnAjouter = new JButton("Ajouter");
        btnSupprimer = new JButton("Supprimer");

        panelForm.add(new JLabel("Nom:"));
        panelForm.add(txtNom);
        panelForm.add(new JLabel("Adresse:"));
        panelForm.add(txtAdresse);
        panelForm.add(new JLabel("Numéro de Téléphone:"));
        panelForm.add(txtNumTel);
        panelForm.add(new JLabel("Email:"));
        panelForm.add(txtEmail);
        panelForm.add(btnAjouter);
        panelForm.add(btnSupprimer);

        add(panelForm, BorderLayout.NORTH);

        // Tableau pour afficher les clients
        model = new DefaultTableModel();
        model.addColumn("Nom");
        model.addColumn("Adresse");
        model.addColumn("Numéro de Téléphone");
        model.addColumn("Email");
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Remplissage du tableau avec les clients existants
        for (Client client : clients) {
            model.addRow(new Object[]{client.getNom(), client.getAdresse(), client.getNumTel(), client.getEmail()});
        }

        // Application de style
        StyleUtil.applyStyle(this);
        
        // Configuration du contrôleur
        ClientControler controller = new ClientControler(this);
        btnAjouter.addActionListener(controller);
        btnSupprimer.addActionListener(controller);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Méthode pour ajouter un client au tableau
    public void addClientToTable(Client client) {
        clients.add(client);
        model.addRow(new Object[]{client.getNom(), client.getAdresse(), client.getNumTel(), client.getEmail()});
    }

    // Méthode pour supprimer un client du tableau
    public void removeClientFromTable(int index) {
        clients.remove(index);
        model.removeRow(index);
    }
    
    // Méthode appelée lorsque les données observées changent
    public void update(Observable o, Object arg) {
        model.setRowCount(0); // Efface les données existantes
        for (Client client : clients) {
            model.addRow(new Object[]{client.getNom(), client.getAdresse(), client.getNumTel(), client.getEmail()});
        }
    }

    // Getters pour accéder aux composants de l'interface utilisateur
    public JTextField getTxtNom() { return txtNom; }
    public JTextField getTxtAdresse() { return txtAdresse; }
    public JTextField getTxtNumTel() { return txtNumTel; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JButton getBtnAjouter() { return btnAjouter; }
    public JButton getBtnSupprimer() { return btnSupprimer; }
    public DefaultTableModel getModel() { return model; }
    public JTable getTable() { return table; }
    public Vector<Client> getClients() { return clients; }
}