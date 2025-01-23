package Vue;

import javax.swing.*;
import java.awt.*;

/**
 * Cette classe utilitaire applique un style cohérent à toutes les fenêtres de l'application.
 * Elle permet d'assurer une uniformité visuelle en définissant des polices, des couleurs et des dispositions similaires pour les composants.
 */

public class StyleUtil {

    public static void applyStyle(JFrame frame) {
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        Font labelFont = new Font("Arial", Font.BOLD, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        for (Component component : frame.getContentPane().getComponents()) {
            if (component instanceof JLabel) {
                component.setFont(labelFont);
            } else if (component instanceof JButton) {
                component.setFont(buttonFont);
                component.setBackground(new Color(70, 130, 180));
                component.setForeground(Color.WHITE);
            } else if (component instanceof JPanel) {
                applyStyleToPanel((JPanel) component);
            }
        }
    }

    private static void applyStyleToPanel(JPanel panel) {
        panel.setBackground(new Color(245, 245, 245));

        for (Component component : panel.getComponents()) {
            if (component instanceof JLabel) {
                component.setFont(new Font("Arial", Font.BOLD, 16));
            } else if (component instanceof JButton) {
                component.setFont(new Font("Arial", Font.BOLD, 14));
                component.setBackground(new Color(70, 130, 180));
                component.setForeground(Color.WHITE);
            } else if (component instanceof JTextField || component instanceof JComboBox) {
                component.setFont(new Font("Arial", Font.PLAIN, 14));
            }
        }
    }
}