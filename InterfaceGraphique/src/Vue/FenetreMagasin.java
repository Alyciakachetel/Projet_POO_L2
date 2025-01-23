package Vue;

import Model.Magasin;
import javax.swing.*;
import java.awt.*;

public class FenetreMagasin extends JFrame {
	
    private JLabel lblBienvenue;
    private JPanel panelBoutons;
    private Magasin magasin;
    private JButton btnProduit;
    private JButton btnClient;
    private JButton btnVente;
    private JButton btnStatistique;

    public FenetreMagasin(Magasin magasin) {
        super("Gestion de Magasin");
        this.magasin = magasin;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        // Label de bienvenue
        lblBienvenue = new JLabel("BIENVENUE DANS LE GESTIONNAIRE DU MAGASIN !");
        lblBienvenue.setHorizontalAlignment(SwingConstants.CENTER);
        lblBienvenue.setFont(new Font("Serif", Font.BOLD, 24));
        lblBienvenue.setForeground(new Color(0, 153, 153));
        add(lblBienvenue, BorderLayout.NORTH);

        // Panneau de boutons pour naviguer entre les différentes fonctionnalités
        panelBoutons = new JPanel(new GridLayout(2, 2, 10, 10));
        panelBoutons.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        btnProduit = new JButton("Gestion Produit");
        btnClient = new JButton("Gestion Client");
        btnVente = new JButton("Gestion Vente");
        btnStatistique = new JButton("Statistique");
        styleButton(btnProduit);
        styleButton(btnClient);
        styleButton(btnVente);
        styleButton(btnStatistique);

        panelBoutons.add(btnProduit);
        panelBoutons.add(btnClient);
        panelBoutons.add(btnVente);
        panelBoutons.add(btnStatistique);

        add(panelBoutons, BorderLayout.CENTER);
    }

    // Méthode pour styliser les boutons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(173, 216, 230));
        button.setForeground(Color.WHITE);
    }

    // Getters pour accéder aux composants de l'interface utilisateur
    public Magasin getMagasin() { return magasin; }
    public JButton getBtnProduit() { return btnProduit; }
    public JButton getBtnClient() { return btnClient; }
    public JButton getBtnVente() { return btnVente; }
    public JButton getBtnStatistique() { return btnStatistique; }
}