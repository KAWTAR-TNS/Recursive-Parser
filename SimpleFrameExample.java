package RecursiveParser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleFrameExample {

    public static void main(String[] args) {
        // Créer la fenêtre principale
        JFrame frame = new JFrame("Projet 1 Compilation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Créer des composants
        JLabel label = new JLabel("Entrez une phrase pour tester :");
        JTextField sentenceInput = new JTextField(20);
        JButton parseButton = new JButton("Vérifier");
        JTextArea resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Police personnalisée
        Font barlowFont = new Font("Barlow", Font.BOLD, 20); // Barlow, Gras, Taille 20

        // Appliquer la police à tous les composants texte
        label.setFont(barlowFont);
        sentenceInput.setFont(barlowFont);
        parseButton.setFont(barlowFont);
        resultArea.setFont(barlowFont);

        // Modifier la couleur de fond et bordures
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(new Color(245, 245, 220)); // Couleur beige
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espacement intérieur

        // Appliquer une bordure stylée au JTextArea (fancy design)
        resultArea.setBorder(new LineBorder(new Color(128, 128, 128), 2)); // Gris avec épaisseur 2
        resultArea.setBackground(new Color(240, 255, 240)); // Vert pâle (Honeydew)

        // Ajouter un espacement entre les composants
        inputPanel.add(label);
        inputPanel.add(sentenceInput);
        inputPanel.add(parseButton);
        inputPanel.setPreferredSize(new Dimension(500, 150));

        // Ajouter un écouteur d'événements pour le bouton
        parseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer la phrase entrée
                String sentence = sentenceInput.getText().trim();
                if (sentence.isEmpty()) {
                    resultArea.setText("Veuillez entrer une phrase.");
                    resultArea.setForeground(Color.BLACK); // Texte noir par défaut
                    return;
                }

                // Analyser la phrase avec le parseur
                simpleFrParser parser = new simpleFrParser(sentence);
                if (parser.parse()) {
                    resultArea.setText("La phrase est valide.");
                    resultArea.setForeground(new Color(0, 100, 0)); // Texte vert
                } else {
                    resultArea.setText("La phrase est invalide.");
                    resultArea.setForeground(Color.RED); // Texte rouge
                }
            }
        });

        // Ajouter les composants au frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Amélioration : centrer le texte dans JTextArea
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);

        // Afficher la fenêtre
        frame.setVisible(true);
    }
}
