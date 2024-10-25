import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.kaoutar.bibliotheque.backend.services.GestionCdService;
import jakarta.naming.InitialContext;
import jakarta.naming.NamingException;

public class CdForm extends JFrame {
    private JTextField titreField;
    private JTextField artisteField;
    private JButton saveButton;

    private GestionCdService gestionCdService; // Déclaration du service

    public CdForm() {
        setTitle("Ajouter CD");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Titre:"));
        titreField = new JTextField();
        add(titreField);

        add(new JLabel("Artiste:"));
        artisteField = new JTextField();
        add(artisteField);

        saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enregistrerCd();
            }
        });
        add(saveButton);

        // Initialiser le service EJB
        initEJB();
    }

    private void initEJB() {
        try {
            InitialContext context = new InitialContext();
            gestionCdService = (GestionCdService) context.lookup("java:global/YourAppName/GestionCdService");
        } catch (NamingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de l'initialisation du service: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void enregistrerCd() {
        String titre = titreField.getText();
        String artiste = artisteField.getText();

        if (titre.isEmpty() || artiste.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String result = gestionCdService.ajouterCd(titre, artiste);
            JOptionPane.showMessageDialog(this, result, "Information", JOptionPane.INFORMATION_MESSAGE);
            titreField.setText("");
            artisteField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CdForm form = new CdForm();
            form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            form.setVisible(true);
        });
    }
}
