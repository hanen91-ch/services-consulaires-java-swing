/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import javax.swing.JPanel;
import modele.DemandePasseport;
import modele.DemandeVisa;
import modele.RegistreUtilisateur;
import modele.Utilisateur;

/**
 *
 * @author Utilisateur
 */
public class MenuView extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuView.class.getName());
    private RegistreUtilisateur listing;
    private Utilisateur utilisateurConnecte;
    private java.awt.CardLayout cardLayout; 
    private java.awt.Panel panelVisa;
    /**
     * Creates new form MenuView
     */
    public MenuView(RegistreUtilisateur listing, Utilisateur utilisateurConnecte) {
        initComponents();
        setTitle("Menu Principale - Services Consulaires");
        this.listing = listing;
        this.utilisateurConnecte = utilisateurConnecte;
        txtNomUser.setText(utilisateurConnecte.getPrenom() + " " + utilisateurConnecte.getNom());
        panelTableauBord = (java.awt.Panel) jPanel2.getComponent(0);

        
        // initialiser le CardLayout 
        this.cardLayout = (java.awt.CardLayout) jPanel2.getLayout();
        
        // afficher panelTableauBord au démarrage
        cardLayout.show(jPanel2,"card5");
        
        
        // remplir les ComboBox
        remplirComboBox();
        
        // ajouter les ActionListeners
        btnSeSoumettre.addActionListener(this::btnSoumettreActionPerformed);
        btnAnnuler.addActionListener(this::btnAnnulerActionPerformed);
    
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        choisirPhoto();
    }
});
    
        remplirProfil();
    }
        
        private void remplirProfil() {
            
            // Remplir les champs du formulaire (jPanel4)
            txtNomProfil.setText(utilisateurConnecte.getNom());
            txtPrenomProfil.setText(utilisateurConnecte.getPrenom());
            txtEmailProfil.setText(utilisateurConnecte.getEmail());
            txtTelephoneProfil.setText(utilisateurConnecte.getTelephone());
            txtAdresseProfil.setText(utilisateurConnecte.getAdresse());
            txtVilleProfil.setText(utilisateurConnecte.getVille());
            txtCPSProfil.setText(utilisateurConnecte.getCodePostal());
            txtPaysProfil.setText(utilisateurConnecte.getPays());
            // Afficher la photo si elle existe
            if (utilisateurConnecte.getCheminPhoto() != null && !utilisateurConnecte.getCheminPhoto().isEmpty()){
                    afficherPhoto(utilisateurConnecte.getCheminPhoto());
            }
        }
        
        
        
        // Méthode pour choisir la photo
        private void choisirPhoto() {
            javax.swing.JFileChooser fc = new javax.swing.JFileChooser();

            // Filtrer uniquement les images
            fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Images", "jpg", "jpeg", "png", "gif"));

            if (fc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
                String chemin = fc.getSelectedFile().getAbsolutePath();

                // Sauvegarder le chemin dans l'objet
                utilisateurConnecte.setCheminPhoto(chemin);

                // Afficher la photo
                afficherPhoto(chemin);
            }
        }

        // Méthode pour afficher la photo dans jLabel46
        private void afficherPhoto(String chemin) {
            if (chemin == null || chemin.isEmpty()) return;

            java.awt.Image img = new javax.swing.ImageIcon(chemin)
                .getImage()
                .getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);

            jLabel46.setIcon(new javax.swing.ImageIcon(img));
            jLabel46.setText(""); // enlever le texte "photo"
        }                                                       
        
        
        
        private void btnSoumettreActionPerformed(java.awt.event.ActionEvent evt) {
            String nom = txtNom1.getText().trim();
            String prenom = txtPrenom1.getText().trim();
            String numPasseport = txtPasseport1.getText().trim();
            String adresse = txtAdresse1.getText().trim();
            String typeVisa = (String) cmbTypeVisa1.getSelectedItem();
            String nationalite = (String) cmbNationnalite1.getSelectedItem();

            if (nom.isEmpty() || prenom.isEmpty() || numPasseport.isEmpty() || adresse.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs obligatoires.",
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            if ("-- Sélectionner --".equals(typeVisa) || "-- Sélectionner --".equals(nationalite)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Veuillez sélectionner le Type de Visa et la Nationalité.",
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            txtStatut1.setText("En attente ");
            txtStatut1.setForeground(new java.awt.Color(255, 102, 0));
            javax.swing.JOptionPane.showMessageDialog(this,
                "Demande soumise avec succès !",
                "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }

        private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {
            txtNom1.setText("");
            txtPrenom1.setText("");
            txtAdresse1.setText("");
            txtPasseport1.setText("");
            txtDateDelivrance.setText("");
            txtMotif.setText("");
            cmbTypeVisa1.setSelectedIndex(0);
            cmbNationnalite1.setSelectedIndex(0);
            cmbJourNaissance.setSelectedIndex(0);
            cmbMoisNaissance.setSelectedIndex(0);
            cmbAnneeNaissance.setSelectedIndex(0);
            cmbJourDebut.setSelectedIndex(0);
            cmbMoisDebut.setSelectedIndex(0);
            cmbAnneeDebut.setSelectedIndex(0);
            cmbJourFin.setSelectedIndex(0);
            cmbMoisFin.setSelectedIndex(0);
            cmbAnneeFin.setSelectedIndex(0);
            chkBillet.setSelected(false);
            chkPasseport.setSelected(false);
            chkHebergement.setSelected(false);
            chkPhoto.setSelected(false);
            txtStatut1.setText("");
    }
   
        // méthode pour remplir ComboBox
        private void remplirComboBox() {
            // Jours
            for (int i = 1; i <= 31; i++) {
                String j = String.format("%02d", i);
                cmbJourNaissance.addItem(j);
                cmbJourDebut.addItem(j);
                cmbJourFin.addItem(j);
            }
            // Mois
            String[] mois = {"Janvier","Février","Mars","Avril",
                             "Mai","Juin","Juillet","Août",
                             "Septembre","Octobre","Novembre","Décembre"};
            for (String m : mois) {
                cmbMoisNaissance.addItem(m);
                cmbMoisDebut.addItem(m);
                cmbMoisFin.addItem(m);
            }
            // Années naissance
            for (int i = 1950; i <= 2026; i++) {
                cmbAnneeNaissance.addItem(String.valueOf(i));
            }
            // Années visa
            for (int i = 2024; i <= 2030; i++) {
                cmbAnneeDebut.addItem(String.valueOf(i));
                cmbAnneeFin.addItem(String.valueOf(i));
            }
            // Type de Visa
            cmbTypeVisa1.addItem("-- Sélectionner --");
            cmbTypeVisa1.addItem("Touriste");
            cmbTypeVisa1.addItem("Travail");
            cmbTypeVisa1.addItem("Étudiant");
            cmbTypeVisa1.addItem("Transit");
            // Nationalité
            cmbNationnalite1.addItem("-- Sélectionner --");
            cmbNationnalite1.addItem("Tunisienne");
            cmbNationnalite1.addItem("Française");
            cmbNationnalite1.addItem("Marocaine");
            cmbNationnalite1.addItem("Canadienne");

            // remplir les ComboBox Passeport
            // Type de Document
            cmbTypeDocument.addItem("-- Sélectionner --");
            cmbTypeDocument.addItem("Passeport");
            cmbTypeDocument.addItem("Carte Nationale d'Identité (CNI)");

            // Motif
            cmbMotifP.addItem("-- Sélectionner --");
            cmbMotifP.addItem("Première demande");
            cmbMotifP.addItem("Renouvellement");
            cmbMotifP.addItem("Perte");
            cmbMotifP.addItem("Vol");
            cmbMotifP.addItem("Détérioration");

            // Nombre de Copies Acte de Naissance
            cmbNbCopiesNaiss.addItem("-- Sélectionner --");
            cmbNbCopiesNaiss.addItem("1");
            cmbNbCopiesNaiss.addItem("2");
            cmbNbCopiesNaiss.addItem("3");
            cmbNbCopiesNaiss.addItem("4");
            cmbNbCopiesNaiss.addItem("5");

            // Nombre de Copies Acte de Mariage
            cmbNbCopiesMariage.addItem("-- Sélectionner --");
            cmbNbCopiesMariage.addItem("1");
            cmbNbCopiesMariage.addItem("2");
            cmbNbCopiesMariage.addItem("3");
            cmbNbCopiesMariage.addItem("4");
            cmbNbCopiesMariage.addItem("5");

            // Jours Naissance Passeport
            for (int i = 1; i <= 31; i++) {
                cmbJourNaissP.addItem(String.format("%02d", i));
            }

            // Mois Naissance Passeport
            String[] moisP = {"Janvier","Février","Mars","Avril",
                              "Mai","Juin","Juillet","Août",
                              "Septembre","Octobre","Novembre","Décembre"};
            for (String m : moisP) {
                cmbMoisNaissP.addItem(m);
            }

            // Années Naissance Passeport
            for (int i = 1950; i <= 2026; i++) {
                cmbAnneeNaissP.addItem(String.valueOf(i));
            }

            // Jours Mariage
            for (int i = 1; i <= 31; i++) {
                cmbJourMariage.addItem(String.format("%02d", i));
            }

            // Mois Mariage
            for (String m : moisP) {
                cmbMoisMariage.addItem(m);
            }

            // Années Mariage
            for (int i = 1950; i <= 2026; i++) {
                cmbAnneeMariage.addItem(String.valueOf(i));
            }
        }
        
        // enregistrer le fichier de sauvegarde visa
        private void sauvegarderVisa() {
            try {
                // Créer le dossier C:\temp s'il n'existe pas
                java.io.File dossier = new java.io.File("C:\\temp");
                if (!dossier.exists()) {
                    dossier.mkdirs();
                }

                String cheminFichier = "C:\\temp\\demandes_visa.txt";
                java.io.FileWriter fw = new java.io.FileWriter(cheminFichier, true);
                java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);

                bw.write("========================================");
                bw.newLine();
                bw.write("Type de Visa      : " + cmbTypeVisa1.getSelectedItem());
                bw.newLine();
                bw.write("--- Informations Personnelles ---");
                bw.newLine();
                bw.write("Nom               : " + txtNom1.getText());
                bw.newLine();
                bw.write("Prénom            : " + txtPrenom1.getText());
                bw.newLine();
                bw.write("N° Passeport      : " + txtPasseport1.getText());
                bw.newLine();
                bw.write("Date de Naissance : " + cmbJourNaissance.getSelectedItem() + "/"
                                                + cmbMoisNaissance.getSelectedItem() + "/"
                                                + cmbAnneeNaissance.getSelectedItem());
                bw.newLine();
                bw.write("Adresse           : " + txtAdresse1.getText());
                bw.newLine();
                bw.write("Nationalité       : " + cmbNationnalite1.getSelectedItem());
                bw.newLine();
                bw.write("--- Informations Visa ---");
                bw.newLine();
                bw.write("Date Début        : " + cmbJourDebut.getSelectedItem() + "/"
                                                + cmbMoisDebut.getSelectedItem() + "/"
                                                + cmbAnneeDebut.getSelectedItem());
                bw.newLine();
                bw.write("Date Fin          : " + cmbJourFin.getSelectedItem() + "/"
                                                + cmbMoisFin.getSelectedItem() + "/"
                                                + cmbAnneeFin.getSelectedItem());
                bw.newLine();
                bw.write("Date Délivrance   : " + txtDateDelivrance.getText());
                bw.newLine();
                bw.write("Motif de Voyage   : " + txtMotif.getText());
                bw.newLine();
                bw.write("--- Documents Fournis ---");
                bw.newLine();
                bw.write("Copie Passeport   : " + (chkPasseport.isSelected() ? "Oui" : "Non"));
                bw.newLine();
                bw.write("Photo Identité    : " + (chkPhoto.isSelected() ? "Oui" : "Non"));
                bw.newLine();
                bw.write("Hébergement       : " + (chkHebergement.isSelected() ? "Oui" : "Non"));
                bw.newLine();
                bw.write("Billet Avion      : " + (chkBillet.isSelected() ? "Oui" : "Non"));
                bw.newLine();
                bw.write("Statut            : " + txtStatut1.getText());
                bw.newLine();
                bw.write("========================================");
                bw.newLine();
                bw.newLine();

                bw.close();
                fw.close();

            } catch (java.io.IOException e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Erreur lors de la sauvegarde : " + e.getMessage(),
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }    
     
        // enregistrer le fichier de sauvegarde passeport    
        private void sauvegarderPasseport() {
            try {
                String cheminFichier = "C:\\temp\\demandes_passeport.txt";
                java.io.FileWriter fw = new java.io.FileWriter(cheminFichier, true);
                java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);

                bw.write("========================================");
                bw.newLine();
                bw.write("Type de Document  : " + cmbTypeDocument.getSelectedItem());
                bw.newLine();
                bw.write("--- Informations Personnelles ---");
                bw.newLine();
                bw.write("Nom               : " + txtNomPasseport.getText());
                bw.newLine();
                bw.write("Prénom            : " + txtPrenomPasseport.getText());
                bw.newLine();
                bw.write("Date de Naissance : " + cmbJourNaissP.getSelectedItem() + "/" 
                                                + cmbMoisNaissP.getSelectedItem() + "/" 
                                                + cmbAnneeNaissP.getSelectedItem());
                bw.newLine();
                bw.write("Adresse           : " + txtAdresseP.getText());
                bw.newLine();
                bw.write("Ville             : " + txtVilleP.getText());
                bw.newLine();
                bw.write("Téléphone         : " + txtTelP.getText());
                bw.newLine();
                bw.write("Email             : " + txtEmailP.getText());
                bw.newLine();
                bw.write("--- Informations Document ---");
                bw.newLine();
                bw.write("Motif             : " + cmbMotifP.getSelectedItem());
                bw.newLine();
                bw.write("Ancien N° Doc     : " + txtAncienDoc.getText());
                bw.newLine();
                bw.write("--- Acte de Naissance ---");
                bw.newLine();
                bw.write("Nom&Prénom Père   : " + txtNomPere.getText());
                bw.newLine();
                bw.write("Nom&Prénom Mère   : " + txtNomMere.getText());
                bw.newLine();
                bw.write("Nombre de Copies  : " + cmbNbCopiesNaiss.getSelectedItem());
                bw.newLine();
                bw.write("--- Acte de Mariage ---");
                bw.newLine();
                bw.write("Nom&Prénom Époux  : " + txtNomEpoux.getText());
                bw.newLine();
                bw.write("Nom&Prénom Épouse : " + txtPrenomEpoux.getText());
                bw.newLine();
                bw.write("Date Mariage      : " + cmbJourMariage.getSelectedItem() + "/" 
                                                + cmbMoisMariage.getSelectedItem() + "/" 
                                                + cmbAnneeMariage.getSelectedItem());
                bw.newLine();
                bw.write("Lieu Mariage      : " + txtLieuMariage.getText());
                bw.newLine();
                bw.write("Nombre de Copies  : " + cmbNbCopiesMariage.getSelectedItem());
                bw.newLine();
                bw.write("Statut            : En attente");
                bw.newLine();
                bw.write("========================================");
                bw.newLine();
                bw.newLine();

                bw.close();
                fw.close();

            } catch (java.io.IOException e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Erreur lors de la sauvegarde : " + e.getMessage(),
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } 
    
        // enregistrer l'impression avec option
        private void afficherOptionsImpression(String contenu, String titre) {
        // Fenêtre d'aperçu
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(contenu);
        textArea.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 500));

        // Boutons personnalisés
        Object[] options = {"Imprimer", "Sauvegarder en TXT", "Sauvegarder en CSV", "Annuler"};

        int choix = javax.swing.JOptionPane.showOptionDialog(
            this,
            scrollPane,
            titre,
            javax.swing.JOptionPane.DEFAULT_OPTION,
            javax.swing.JOptionPane.PLAIN_MESSAGE,
            null,
            options,
            options[0]
        );

        if (choix == 0) {
            // Imprimer
            try {
                textArea.print();
            } catch (java.awt.print.PrinterException e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Erreur impression : " + e.getMessage(),
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            }

        } else if (choix == 1) {
            // Sauvegarder en TXT
            javax.swing.JFileChooser fc = new javax.swing.JFileChooser("C:\\temp");
            fc.setSelectedFile(new java.io.File("accuse_reception.txt"));
            if (fc.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
                try (java.io.FileWriter fw = new java.io.FileWriter(fc.getSelectedFile())) {
                    fw.write(contenu);
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Fichier TXT sauvegardé avec succès !",
                        "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } catch (java.io.IOException e) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Erreur sauvegarde TXT : " + e.getMessage(),
                        "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }

        } else if (choix == 2) {
            // Sauvegarder en CSV
            javax.swing.JFileChooser fc = new javax.swing.JFileChooser("C:\\temp");
            fc.setSelectedFile(new java.io.File("accuse_reception.csv"));
            if (fc.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
                try (java.io.FileWriter fw = new java.io.FileWriter(fc.getSelectedFile())) {
                    // Convertir le contenu en CSV
                    fw.write(convertirEnCSV(contenu));
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Fichier CSV sauvegardé avec succès !",
                        "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } catch (java.io.IOException e) {
                    javax.swing.JOptionPane.showMessageDialog(this,
                        "Erreur sauvegarde CSV : " + e.getMessage(),
                        "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
        // convertir document en csv
        private String convertirEnCSV(String contenu) {
            StringBuilder csv = new StringBuilder();
            csv.append("Champ;Valeur\n");

            for (String ligne : contenu.split("\n")) {
                if (ligne.contains(" : ")) {
                    String[] parts = ligne.split(" : ", 2);
                    csv.append(parts[0].trim())
                       .append(";")
                       .append(parts[1].trim())
                       .append("\n");
                }
            }
            return csv.toString();
}
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNom = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrenom = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnEnregistrer = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtSeConnecter = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMotDePasse = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnVisa = new javax.swing.JButton();
        btnPasseport = new javax.swing.JButton();
        btnRendezVous = new javax.swing.JButton();
        btnDocuments = new javax.swing.JButton();
        btnProfil = new javax.swing.JButton();
        btnTableauBord = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelTableauBord = new java.awt.Panel();
        java.awt.Panel panelVisa = new java.awt.Panel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtNom1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPrenom1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtPasseport1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cmbJourNaissance = new javax.swing.JComboBox<>();
        cmbMoisNaissance = new javax.swing.JComboBox<>();
        cmbAnneeNaissance = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cmbTypeVisa1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtAdresse1 = new javax.swing.JTextField();
        cmbNationnalite1 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cmbAnneeDebut = new javax.swing.JComboBox<>();
        cmbMoisDebut = new javax.swing.JComboBox<>();
        cmbJourDebut = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        cmbAnneeFin = new javax.swing.JComboBox<>();
        cmbMoisFin = new javax.swing.JComboBox<>();
        cmbJourFin = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        chkPasseport = new javax.swing.JCheckBox();
        chkPhoto = new javax.swing.JCheckBox();
        chkHebergement = new javax.swing.JCheckBox();
        chkBillet = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        txtDateDelivrance = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMotif = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        txtStatut1 = new javax.swing.JLabel();
        btnAnnuler = new javax.swing.JButton();
        btnSeSoumettre = new javax.swing.JButton();
        btnImprimerVisa = new javax.swing.JButton();
        panelPasseport = new java.awt.Panel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtTypeDoc = new javax.swing.JLabel();
        cmbTypeDocument = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        cmbMotifP = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        txtAncienDoc = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtEmailP = new javax.swing.JTextField();
        txtNomP = new javax.swing.JLabel();
        txtLieuMariage = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtAdresseP = new javax.swing.JTextField();
        txtNomPasseport = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        txtVilleP = new javax.swing.JTextField();
        txtPrenomPasseport = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        cmbJourNaissP = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        cmbMoisNaissP = new javax.swing.JComboBox<>();
        txtTelP = new javax.swing.JTextField();
        cmbAnneeNaissP = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        txtNomPere = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtNomMere = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        cmbNbCopiesNaiss = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtNomEpoux = new javax.swing.JTextField();
        txtPrenomEpoux = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        cmbJourMariage = new javax.swing.JComboBox<>();
        cmbMoisMariage = new javax.swing.JComboBox<>();
        cmbAnneeMariage = new javax.swing.JComboBox<>();
        cmbNbCopiesMariage = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        btnAnnulerP = new javax.swing.JButton();
        btnSoumettreP = new javax.swing.JButton();
        btnImptimerP = new javax.swing.JButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        txtStatut = new java.awt.Label();
        panelRendezVous = new java.awt.Panel();
        panelDocuments = new java.awt.Panel();
        panelProfil = new java.awt.Panel();
        jPanel3 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtTotalDossier = new javax.swing.JTextField();
        txtDossierAccepte = new javax.swing.JTextField();
        txtDossierEnCours = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        txtNomProfil = new javax.swing.JTextField();
        txtPrenomProfil = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtTelephoneProfil = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtEmailProfil = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtAdresseProfil = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtPaysProfil = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtCPSProfil = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtVilleProfil = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnAnnulerProfil = new javax.swing.JButton();
        btnMAJProfil = new javax.swing.JButton();
        btnSeDeconnecte = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        txtNomUser = new javax.swing.JLabel();

        jFrame1.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setText("Services Consulaires");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("INSCRIPTION AU PORTAIL");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("NOM * :");

        txtNom.setMinimumSize(new java.awt.Dimension(64, 25));
        txtNom.setPreferredSize(new java.awt.Dimension(73, 25));
        txtNom.addActionListener(this::txtNomActionPerformed);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("PRÉNOM * :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("ADRESSE EMAIL * :");

        jLabel7.setText("MOT DE PASSE * :");

        txtPrenom.setPreferredSize(new java.awt.Dimension(73, 25));
        txtPrenom.addActionListener(this::txtPrenomActionPerformed);

        txtEmail.setPreferredSize(new java.awt.Dimension(73, 25));
        txtEmail.addActionListener(this::txtEmailActionPerformed);

        btnEnregistrer.setBackground(new java.awt.Color(204, 204, 0));
        btnEnregistrer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEnregistrer.setText("S'INSCRIRE");
        btnEnregistrer.setPreferredSize(new java.awt.Dimension(116, 23));
        btnEnregistrer.addActionListener(this::btnEnregistrerActionPerformed);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel9.setText("Vous avez déjà un compte ?");

        txtSeConnecter.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSeConnecter.setForeground(new java.awt.Color(204, 204, 0));
        txtSeConnecter.setText("Se Connecter ...");
        txtSeConnecter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSeConnecterMouseClicked(evt);
            }
        });

        jLabel11.setBackground(new java.awt.Color(153, 153, 153));
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("Collége Bois-de-Boulogne                POO Session 2025");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jFrame1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jFrame1Layout.createSequentialGroup()
                                    .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtPrenom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jFrame1Layout.createSequentialGroup()
                                    .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                        .addComponent(txtMotDePasse))))
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(txtSeConnecter, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jFrame1Layout.createSequentialGroup()
                        .addComponent(txtMotDePasse, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGap(32, 32, 32)
                .addComponent(btnEnregistrer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSeConnecter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18))
        );

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        btnVisa.setBackground(new java.awt.Color(0, 153, 204));
        btnVisa.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnVisa.setForeground(new java.awt.Color(255, 255, 255));
        btnVisa.setText("Demandes de Visa");
        btnVisa.addActionListener(this::btnVisaActionPerformed);

        btnPasseport.setBackground(new java.awt.Color(0, 153, 204));
        btnPasseport.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnPasseport.setForeground(new java.awt.Color(255, 255, 255));
        btnPasseport.setText("Passeports & CNI");
        btnPasseport.addActionListener(this::btnPasseportActionPerformed);

        btnRendezVous.setBackground(new java.awt.Color(0, 153, 204));
        btnRendezVous.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRendezVous.setForeground(new java.awt.Color(255, 255, 255));
        btnRendezVous.setText("Rendez-vous");
        btnRendezVous.addActionListener(this::btnRendezVousActionPerformed);

        btnDocuments.setBackground(new java.awt.Color(0, 153, 204));
        btnDocuments.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnDocuments.setForeground(new java.awt.Color(255, 255, 255));
        btnDocuments.setText("Envoi Documents");
        btnDocuments.addActionListener(this::btnDocumentsActionPerformed);

        btnProfil.setBackground(new java.awt.Color(0, 153, 204));
        btnProfil.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnProfil.setForeground(new java.awt.Color(255, 255, 255));
        btnProfil.setText("Mon Profil");
        btnProfil.addActionListener(this::btnProfilActionPerformed);

        btnTableauBord.setBackground(new java.awt.Color(0, 153, 204));
        btnTableauBord.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTableauBord.setForeground(new java.awt.Color(255, 255, 255));
        btnTableauBord.setText("Tableau de bord");
        btnTableauBord.addActionListener(this::btnTableauBordActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 57, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnVisa, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(btnPasseport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRendezVous, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDocuments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTableauBord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(btnTableauBord, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(69, 69, 69)
                .addComponent(btnVisa, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnPasseport, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnRendezVous, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnDocuments, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                .addGap(73, 73, 73)
                .addComponent(btnProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                .addGap(41, 41, 41))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.CardLayout());

        panelTableauBord.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelTableauBordLayout = new javax.swing.GroupLayout(panelTableauBord);
        panelTableauBord.setLayout(panelTableauBordLayout);
        panelTableauBordLayout.setHorizontalGroup(
            panelTableauBordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );
        panelTableauBordLayout.setVerticalGroup(
            panelTableauBordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        jPanel2.add(panelTableauBord, "card5");

        panelVisa.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel8.setText("Services Consulaires");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("FORMULAIRE DEMANDE VISA");

        jLabel12.setText("Nom :");

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setDoubleBuffered(true);

        txtNom1.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel13.setText("Prénom :");

        txtPrenom1.setPreferredSize(new java.awt.Dimension(73, 25));
        txtPrenom1.addActionListener(this::txtPrenom1ActionPerformed);

        jLabel14.setText("N.Passeport :");
        jLabel14.setPreferredSize(new java.awt.Dimension(51, 25));

        txtPasseport1.setMinimumSize(new java.awt.Dimension(64, 25));
        txtPasseport1.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel15.setText("Date de Naissance :");

        cmbJourNaissance.setPreferredSize(new java.awt.Dimension(72, 25));

        cmbMoisNaissance.setPreferredSize(new java.awt.Dimension(72, 25));

        cmbAnneeNaissance.setPreferredSize(new java.awt.Dimension(72, 25));

        jLabel16.setText("Type de Visa :");
        jLabel16.setPreferredSize(new java.awt.Dimension(67, 25));

        cmbTypeVisa1.setPreferredSize(new java.awt.Dimension(72, 25));

        jLabel17.setText("Adresse :");

        txtAdresse1.setPreferredSize(new java.awt.Dimension(73, 25));

        cmbNationnalite1.setPreferredSize(new java.awt.Dimension(72, 25));

        jLabel18.setText("Nationnalité :");

        cmbAnneeDebut.setMinimumSize(new java.awt.Dimension(72, 25));

        cmbMoisDebut.setMinimumSize(new java.awt.Dimension(72, 25));

        cmbJourDebut.setMinimumSize(new java.awt.Dimension(72, 25));

        jLabel19.setText("Date Début :");
        jLabel19.setPreferredSize(new java.awt.Dimension(62, 25));

        cmbAnneeFin.setMinimumSize(new java.awt.Dimension(72, 25));
        cmbAnneeFin.setPreferredSize(new java.awt.Dimension(72, 25));

        cmbMoisFin.setMinimumSize(new java.awt.Dimension(72, 25));
        cmbMoisFin.setPreferredSize(new java.awt.Dimension(72, 25));

        cmbJourFin.setMinimumSize(new java.awt.Dimension(72, 25));
        cmbJourFin.setPreferredSize(new java.awt.Dimension(72, 25));

        jLabel20.setText("Date Fin :");
        jLabel20.setPreferredSize(new java.awt.Dimension(44, 25));

        chkPasseport.setText("copie passeport");
        chkPasseport.setActionCommand("Copie Passeport");

        chkPhoto.setText("Photo identité");

        chkHebergement.setText("Justificatif hébergement");

        chkBillet.setText("Billet avion");
        chkBillet.setActionCommand("Copie Passeport");

        jLabel21.setText("Motif de Voyage");

        txtDateDelivrance.setMinimumSize(new java.awt.Dimension(64, 25));
        txtDateDelivrance.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel22.setText("Date de Délivrance :");
        jLabel22.setPreferredSize(new java.awt.Dimension(51, 25));

        txtMotif.setColumns(20);
        txtMotif.setRows(5);
        jScrollPane2.setViewportView(txtMotif);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Statut :");

        txtStatut1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        btnAnnuler.setBackground(new java.awt.Color(255, 153, 0));
        btnAnnuler.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAnnuler.setText("Annuler");

        btnSeSoumettre.setBackground(new java.awt.Color(102, 204, 0));
        btnSeSoumettre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSeSoumettre.setText("Soumettre");
        btnSeSoumettre.addActionListener(this::btnSeSoumettreActionPerformed);

        btnImprimerVisa.setBackground(new java.awt.Color(0, 153, 153));
        btnImprimerVisa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnImprimerVisa.setText("Imprimer");
        btnImprimerVisa.addActionListener(this::btnImprimerVisaActionPerformed);

        javax.swing.GroupLayout panelVisaLayout = new javax.swing.GroupLayout(panelVisa);
        panelVisa.setLayout(panelVisaLayout);
        panelVisaLayout.setHorizontalGroup(
            panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisaLayout.createSequentialGroup()
                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisaLayout.createSequentialGroup()
                                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNom1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbJourNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMoisNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAnneeNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPasseport1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cmbJourDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMoisDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAnneeDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelVisaLayout.createSequentialGroup()
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelVisaLayout.createSequentialGroup()
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtDateDelivrance, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisaLayout.createSequentialGroup()
                                            .addGap(47, 47, 47)
                                            .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(panelVisaLayout.createSequentialGroup()
                                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(cmbJourFin, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cmbMoisFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cmbAnneeFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(panelVisaLayout.createSequentialGroup()
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(cmbNationnalite1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPrenom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelVisaLayout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnImprimerVisa)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnSeSoumettre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnAnnuler))
                                .addGroup(panelVisaLayout.createSequentialGroup()
                                    .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelVisaLayout.createSequentialGroup()
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbTypeVisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelVisaLayout.createSequentialGroup()
                                            .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(chkBillet)
                                                .addComponent(chkPasseport))
                                            .addGap(135, 135, 135)
                                            .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(chkPhoto)
                                                .addComponent(chkHebergement))))
                                    .addGap(328, 328, 328)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisaLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStatut1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        panelVisaLayout.setVerticalGroup(
            panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeSoumettre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimerVisa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelVisaLayout.createSequentialGroup()
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNom1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(cmbJourNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMoisNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAnneeNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPasseport1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTypeVisa1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbJourDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMoisDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAnneeDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelVisaLayout.createSequentialGroup()
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrenom1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDateDelivrance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbNationnalite1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbJourFin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMoisFin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAnneeFin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkBillet)
                    .addComponent(chkHebergement))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkPasseport)
                    .addComponent(chkPhoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatut1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelVisa, "panelVisa");

        panelPasseport.setBackground(new java.awt.Color(204, 204, 204));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel24.setText("FORMULAIRE DEMANDE PASSEPORT & CNI");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel25.setText("Services Consulaires");

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setDoubleBuffered(true);

        txtTypeDoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTypeDoc.setText("Type de Document :");

        cmbTypeDocument.setPreferredSize(new java.awt.Dimension(72, 25));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Informations Documents : ");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("Informations Personnelles : ");

        cmbMotifP.setMinimumSize(new java.awt.Dimension(72, 25));
        cmbMotifP.setPreferredSize(new java.awt.Dimension(72, 25));

        jLabel36.setText("Motif");

        jLabel37.setText("Ancien N° Document :");
        jLabel37.setMaximumSize(new java.awt.Dimension(117, 25));
        jLabel37.setMinimumSize(new java.awt.Dimension(117, 25));
        jLabel37.setPreferredSize(new java.awt.Dimension(117, 25));

        txtAncienDoc.setPreferredSize(new java.awt.Dimension(64, 25));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("Acte de Naissance : ");

        jLabel35.setText("Email : ");

        jLabel30.setText("Lieu Marriage :");
        jLabel30.setPreferredSize(new java.awt.Dimension(100, 25));

        txtEmailP.setMinimumSize(new java.awt.Dimension(64, 25));
        txtEmailP.setPreferredSize(new java.awt.Dimension(64, 25));

        txtNomP.setText("Nom :");

        txtLieuMariage.setMinimumSize(new java.awt.Dimension(64, 25));
        txtLieuMariage.setPreferredSize(new java.awt.Dimension(64, 25));

        jLabel31.setText("Adresse :");
        jLabel31.setMaximumSize(new java.awt.Dimension(47, 25));

        txtAdresseP.setMinimumSize(new java.awt.Dimension(64, 25));
        txtAdresseP.setPreferredSize(new java.awt.Dimension(64, 25));

        txtNomPasseport.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel32.setText("Ville :");

        jLabel28.setText("Prénom : ");
        jLabel28.setMaximumSize(new java.awt.Dimension(50, 25));

        txtVilleP.setMinimumSize(new java.awt.Dimension(64, 25));
        txtVilleP.setPreferredSize(new java.awt.Dimension(64, 25));

        txtPrenomPasseport.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel29.setText("Date de Naissance :");

        cmbJourNaissP.setPreferredSize(new java.awt.Dimension(72, 25));
        cmbJourNaissP.addActionListener(this::cmbJourNaissPActionPerformed);

        jLabel34.setText("Téléphone :");

        cmbMoisNaissP.setMinimumSize(new java.awt.Dimension(72, 25));
        cmbMoisNaissP.setPreferredSize(new java.awt.Dimension(72, 25));

        txtTelP.setMinimumSize(new java.awt.Dimension(64, 25));
        txtTelP.setPreferredSize(new java.awt.Dimension(64, 25));

        cmbAnneeNaissP.setPreferredSize(new java.awt.Dimension(72, 25));

        jLabel33.setText("Nom&Prénom Pére :");
        jLabel33.setPreferredSize(new java.awt.Dimension(109, 25));

        txtNomPere.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel39.setText("Nom&Prénom Mére :");
        jLabel39.setPreferredSize(new java.awt.Dimension(113, 25));

        txtNomMere.setPreferredSize(new java.awt.Dimension(73, 25));
        txtNomMere.addActionListener(this::txtNomMereActionPerformed);

        jLabel40.setText("Nombre de Copies :");

        cmbNbCopiesNaiss.setPreferredSize(new java.awt.Dimension(72, 25));
        cmbNbCopiesNaiss.addActionListener(this::cmbNbCopiesNaissActionPerformed);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel41.setText("Acte de Marriage : ");

        jLabel42.setText("Nom&Prénom Époux :");
        jLabel42.setPreferredSize(new java.awt.Dimension(109, 25));

        txtNomEpoux.setPreferredSize(new java.awt.Dimension(73, 25));
        txtNomEpoux.addActionListener(this::txtNomEpouxActionPerformed);

        txtPrenomEpoux.setPreferredSize(new java.awt.Dimension(73, 25));
        txtPrenomEpoux.addActionListener(this::txtPrenomEpouxActionPerformed);

        jLabel43.setText("Nom&Prénom Épouse :");
        jLabel43.setPreferredSize(new java.awt.Dimension(113, 25));

        jLabel44.setText("Date Marriage :");

        cmbJourMariage.setPreferredSize(new java.awt.Dimension(72, 25));
        cmbJourMariage.addActionListener(this::cmbJourMariageActionPerformed);

        cmbMoisMariage.setMinimumSize(new java.awt.Dimension(72, 25));
        cmbMoisMariage.setPreferredSize(new java.awt.Dimension(72, 25));

        cmbAnneeMariage.setPreferredSize(new java.awt.Dimension(72, 25));

        cmbNbCopiesMariage.setPreferredSize(new java.awt.Dimension(72, 25));
        cmbNbCopiesMariage.addActionListener(this::cmbNbCopiesMariageActionPerformed);

        jLabel45.setText("Nombre de Copies :");

        btnAnnulerP.setBackground(new java.awt.Color(255, 102, 0));
        btnAnnulerP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAnnulerP.setText("Annuler");
        btnAnnulerP.addActionListener(this::btnAnnulerPActionPerformed);

        btnSoumettreP.setBackground(new java.awt.Color(0, 204, 0));
        btnSoumettreP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSoumettreP.setText("Soumettre");
        btnSoumettreP.addActionListener(this::btnSoumettrePActionPerformed);

        btnImptimerP.setBackground(new java.awt.Color(0, 153, 153));
        btnImptimerP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnImptimerP.setText("Imprimer");
        btnImptimerP.addActionListener(this::btnImptimerPActionPerformed);

        label1.setText("label1");

        label2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label2.setText("Statut : ");

        label3.setText("label3");

        txtStatut.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelPasseportLayout = new javax.swing.GroupLayout(panelPasseport);
        panelPasseport.setLayout(panelPasseportLayout);
        panelPasseportLayout.setHorizontalGroup(
            panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPasseportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPasseportLayout.createSequentialGroup()
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImptimerP)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSoumettreP)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAnnulerP))
                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelPasseportLayout.createSequentialGroup()
                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelPasseportLayout.createSequentialGroup()
                                    .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addGap(11, 11, 11)
                                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtNomP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(panelPasseportLayout.createSequentialGroup()
                                                    .addComponent(cmbJourNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cmbMoisNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cmbAnneeNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPasseportLayout.createSequentialGroup()
                                                    .addComponent(txtNomPasseport, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10, 10, 10))))
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(51, 51, 51)
                                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(panelPasseportLayout.createSequentialGroup()
                                                    .addComponent(txtTelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGap(32, 32, 32)
                                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtAdresseP, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addGap(15, 15, 15)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtVilleP, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtEmailP, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(panelPasseportLayout.createSequentialGroup()
                                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtPrenomPasseport, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelPasseportLayout.createSequentialGroup()
                                    .addComponent(txtTypeDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbTypeDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelPasseportLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbMotifP, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(31, 31, 31)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtAncienDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNomPere, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNomMere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addComponent(jLabel40)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmbNbCopiesNaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmbJourMariage, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbMoisMariage, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cmbAnneeMariage, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(5, 5, 5)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtLieuMariage, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtNomEpoux, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtPrenomEpoux, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addComponent(jLabel45)
                                            .addGap(18, 18, 18)
                                            .addComponent(cmbNbCopiesMariage, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtStatut, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGap(8, 8, 8))))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        panelPasseportLayout.setVerticalGroup(
            panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPasseportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(btnAnnulerP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoumettreP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImptimerP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTypeDoc)
                    .addComponent(cmbTypeDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel32)
                                .addComponent(txtVilleP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNomPasseport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNomP)
                                    .addComponent(txtPrenomPasseport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmbJourNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbMoisNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbAnneeNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAdresseP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPasseportLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtTelP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel34)))
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(txtEmailP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMotifP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAncienDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomMere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNomPere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40)
                                    .addComponent(cmbNbCopiesNaiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNomEpoux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrenomEpoux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cmbJourMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbMoisMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbAnneeMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLieuMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel45))
                            .addComponent(label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStatut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(cmbNbCopiesMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelPasseport, "card3");

        panelRendezVous.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelRendezVousLayout = new javax.swing.GroupLayout(panelRendezVous);
        panelRendezVous.setLayout(panelRendezVousLayout);
        panelRendezVousLayout.setHorizontalGroup(
            panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );
        panelRendezVousLayout.setVerticalGroup(
            panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        jPanel2.add(panelRendezVous, "card4");

        panelDocuments.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelDocumentsLayout = new javax.swing.GroupLayout(panelDocuments);
        panelDocuments.setLayout(panelDocumentsLayout);
        panelDocumentsLayout.setHorizontalGroup(
            panelDocumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );
        panelDocumentsLayout.setVerticalGroup(
            panelDocumentsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );

        jPanel2.add(panelDocuments, "card6");

        panelProfil.setBackground(new java.awt.Color(204, 204, 204));

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setToolTipText("");
        jLabel46.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel48.setText("Dossiers : ");

        jLabel59.setText("Acceptés :");

        jLabel60.setText("En Cours :");

        txtTotalDossier.addActionListener(this::txtTotalDossierActionPerformed);

        jLabel61.setText("Mon Photo Profil");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTotalDossier))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDossierAccepte, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtDossierEnCours)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel61)
                .addGap(135, 135, 135)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(txtTotalDossier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(txtDossierAccepte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDossierEnCours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jLabel46.getAccessibleContext().setAccessibleName("");
        jLabel48.getAccessibleContext().setAccessibleName("Dossiers : ");

        jLabel49.setText("Nom : ");

        jLabel50.setText("Prénom : ");

        txtNomProfil.addActionListener(this::txtNomProfilActionPerformed);

        jLabel51.setText("Téléphone :");

        txtTelephoneProfil.addActionListener(this::txtTelephoneProfilActionPerformed);

        jLabel52.setText("Email : ");

        jLabel53.setText("Adresse :");

        txtAdresseProfil.addActionListener(this::txtAdresseProfilActionPerformed);

        jLabel54.setText("Pays :");

        txtPaysProfil.addActionListener(this::txtPaysProfilActionPerformed);

        jLabel55.setText("Code Postale : ");

        txtCPSProfil.addActionListener(this::txtCPSProfilActionPerformed);

        jLabel56.setText("Ville : ");

        txtVilleProfil.addActionListener(this::txtVilleProfilActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGap(181, 181, 181)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtVilleProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCPSProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(txtPaysProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(223, 223, 223))))
                    .addComponent(txtAdresseProfil, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTelephoneProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtPrenomProfil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                        .addComponent(txtNomProfil, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(txtEmailProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrenomProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtTelephoneProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtAdresseProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVilleProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56)
                    .addComponent(jLabel54)
                    .addComponent(txtPaysProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txtCPSProfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel57.setText("Services Consulaires");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel58.setText("MON PROFIL");

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator5.setDoubleBuffered(true);

        btnAnnulerProfil.setBackground(new java.awt.Color(255, 102, 0));
        btnAnnulerProfil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAnnulerProfil.setText("Annuler");
        btnAnnulerProfil.addActionListener(this::btnAnnulerProfilActionPerformed);

        btnMAJProfil.setBackground(new java.awt.Color(102, 102, 102));
        btnMAJProfil.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnMAJProfil.setText("Mettre a jour");
        btnMAJProfil.addActionListener(this::btnMAJProfilActionPerformed);

        javax.swing.GroupLayout panelProfilLayout = new javax.swing.GroupLayout(panelProfil);
        panelProfil.setLayout(panelProfilLayout);
        panelProfilLayout.setHorizontalGroup(
            panelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(panelProfilLayout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMAJProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnnulerProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelProfilLayout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelProfilLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelProfilLayout.setVerticalGroup(
            panelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProfilLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(btnAnnulerProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMAJProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.add(panelProfil, "card7");

        btnSeDeconnecte.setBackground(new java.awt.Color(0, 102, 153));
        btnSeDeconnecte.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSeDeconnecte.setForeground(new java.awt.Color(255, 255, 255));
        btnSeDeconnecte.setText("Se déconnecter");
        btnSeDeconnecte.addActionListener(this::btnSeDeconnecteActionPerformed);

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Connecté en tant que :");

        txtNomUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomUser, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSeDeconnecte, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeDeconnecte, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel47)
                    .addComponent(txtNomUser))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeDeconnecteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeDeconnecteActionPerformed
        // TODO add your handling code here:
        new LoginView(listing).setVisible(true);
        System.exit(0); // ferme toute l'application
        
    }//GEN-LAST:event_btnSeDeconnecteActionPerformed

    private void btnVisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisaActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "panelVisa");
        //panelVisa.setVisible(false);
        //panelVisa.setVisible(true);
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_btnVisaActionPerformed

    private void btnPasseportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasseportActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "card3");
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_btnPasseportActionPerformed

    private void btnRendezVousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRendezVousActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "card4");
        jPanel2.revalidate();
        jPanel2.repaint();
        
    }//GEN-LAST:event_btnRendezVousActionPerformed

    private void btnTableauBordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTableauBordActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "card5");
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_btnTableauBordActionPerformed

    private void btnDocumentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentsActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "card6");
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_btnDocumentsActionPerformed

    private void btnProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfilActionPerformed
        // TODO add your handling code here:
        cardLayout.show(jPanel2, "card7");
        jPanel2.revalidate();
        jPanel2.repaint();
    }//GEN-LAST:event_btnProfilActionPerformed

    private void txtNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomActionPerformed

    private void txtPrenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrenomActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnregistrerActionPerformed
        // TODO add your handling code here:
        try{
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String email = txtEmail.getText();
            String motDePasse = new String(txtMotDePasse.getPassword());

            // Vérifier si les champs sont vides
            if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs.",
                    "Erreur",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Vérifier si l'email existe déjà
            if (listing.emailExiste(email)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Cet email est déjà utilisé.",
                    "Erreur",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Créer et ajouter l'utilisateur
            Utilisateur u = new Utilisateur(nom, prenom, email, motDePasse);
            listing.ajouterUtilisateur(u);

            javax.swing.JOptionPane.showMessageDialog(this,
                "Compte créé avec succès !",
                "Succès",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

            //this.dispose();  // ferme la fenetre courante

        }   catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Une erreur inattendue s'est produite : " + e.getMessage(),
                "Erreur",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEnregistrerActionPerformed

    private void txtSeConnecterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSeConnecterMouseClicked
        // TODO add your handling code here:
        new LoginView(listing).setVisible(true);
        this.dispose(); // ferme la fenetre courante
    }//GEN-LAST:event_txtSeConnecterMouseClicked

    private void txtPrenom1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrenom1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrenom1ActionPerformed

    private void btnSeSoumettreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeSoumettreActionPerformed
        // TODO add your handling code here:
        String nom = txtNom1.getText().trim();
        String prenom = txtPrenom1.getText().trim();
        String numPasseport = txtPasseport1.getText().trim();
        String adresse = txtAdresse1.getText().trim();
        String typeVisa = (String) cmbTypeVisa1.getSelectedItem();
        String nationalite = (String) cmbNationnalite1.getSelectedItem();
        
        // validation
        if (nom.isEmpty() || prenom.isEmpty() || numPasseport.isEmpty() || adresse.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez remplir tous les champs obligatoires.",
                "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ("-- Sélectionner --".equals(typeVisa) || "-- Sélectionner --".equals(nationalite)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez sélectionner le Type de Visa et la Nationalité.",
                "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // création de l'objet DemandeVisa
        DemandeVisa demande = new DemandeVisa();
        demande.setNom(nom);
        demande.setPrenom(prenom);
        demande.setAdresse(adresse);
        demande.setNumPasseport(numPasseport);
        demande.setTypeVisa(typeVisa);
        demande.setNationalite(nationalite);
        demande.setDateNaissance(cmbJourNaissance.getSelectedItem() + "/"
                               + cmbMoisNaissance.getSelectedItem() + "/"
                               + cmbAnneeNaissance.getSelectedItem());
        demande.setDateDebut(cmbJourDebut.getSelectedItem() + "/"
                           + cmbMoisDebut.getSelectedItem() + "/"
                           + cmbAnneeDebut.getSelectedItem());
        demande.setDateFin(cmbJourFin.getSelectedItem() + "/"
                         + cmbMoisFin.getSelectedItem() + "/"
                         + cmbAnneeFin.getSelectedItem());
        demande.setMotifVoyage(txtMotif.getText());
        demande.setCopiePasseport(chkPasseport.isSelected());
        demande.setPhotoIdentite(chkPhoto.isSelected());
        demande.setJustifHebergement(chkHebergement.isSelected());
        demande.setBilletAvion(chkBillet.isSelected());
    
        // Appeler soumettre() de la classe
        demande.soumettre();
        
        // Mettre à jour le statut
        txtStatut1.setText("En attente ");
        txtStatut1.setForeground(new java.awt.Color(255, 102, 0));
    
        // sauvegarder dans txt
        sauvegarderVisa();

        // Message succés
        javax.swing.JOptionPane.showMessageDialog(this,
            "Demande soumise avec succès !",
            "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    
    }//GEN-LAST:event_btnSeSoumettreActionPerformed

    private void cmbJourNaissPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJourNaissPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJourNaissPActionPerformed

    private void cmbNbCopiesNaissActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNbCopiesNaissActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNbCopiesNaissActionPerformed

    private void txtNomMereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomMereActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomMereActionPerformed

    private void txtPrenomEpouxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrenomEpouxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrenomEpouxActionPerformed

    private void cmbJourMariageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJourMariageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJourMariageActionPerformed

    private void cmbNbCopiesMariageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNbCopiesMariageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNbCopiesMariageActionPerformed

    private void btnAnnulerPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerPActionPerformed
        // TODO add your handling code here:
         // Vider les informations personnelles
        txtNomPasseport.setText("");
        txtPrenomPasseport.setText("");
        txtAdresseP.setText("");
        txtVilleP.setText("");
        txtTelP.setText("");
        txtEmailP.setText("");
        txtAncienDoc.setText("");

        // Vider les actes
        txtNomPere.setText("");
        txtNomMere.setText("");
        txtNomEpoux.setText("");
        txtPrenomEpoux.setText("");
        txtLieuMariage.setText("");

        // Réinitialiser les ComboBox
        cmbTypeDocument.setSelectedIndex(0);
        cmbMotifP.setSelectedIndex(0);
        cmbNbCopiesNaiss.setSelectedIndex(0);
        cmbNbCopiesMariage.setSelectedIndex(0);
        cmbJourNaissP.setSelectedIndex(0);
        cmbMoisNaissP.setSelectedIndex(0);
        cmbAnneeNaissP.setSelectedIndex(0);
        cmbJourMariage.setSelectedIndex(0);
        cmbMoisMariage.setSelectedIndex(0);
        cmbAnneeMariage.setSelectedIndex(0);

        // Réinitialiser le statut
        txtStatut.setText("");
    }//GEN-LAST:event_btnAnnulerPActionPerformed

    private void txtNomEpouxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomEpouxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomEpouxActionPerformed

    private void btnSoumettrePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoumettrePActionPerformed
        // TODO add your handling code here:
        
        String nom = txtNomPasseport.getText().trim();
        String prenom = txtPrenomPasseport.getText().trim();
        String adresse = txtAdresseP.getText().trim();
        String typeDoc = (String) cmbTypeDocument.getSelectedItem();
        String motif = (String) cmbMotifP.getSelectedItem();
        
        // validation
        if (nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez remplir les champs obligatoires (Nom, Prénom, Adresse).",
                "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ("-- Sélectionner --".equals(typeDoc) || typeDoc == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez sélectionner un Type de Document.",
                "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if ("-- Sélectionner --".equals(motif) || motif == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez sélectionner un Motif.",
                "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Créer l'objet DemandePasseport
        DemandePasseport demande = new DemandePasseport();
        demande.setNom(nom);
        demande.setPrenom(prenom);
        demande.setAdresse(adresse);
        demande.setVille(txtVilleP.getText().trim());
        demande.setTelephone(txtTelP.getText().trim());
        demande.setEmail(txtEmailP.getText().trim());
        demande.setTypeDocument(typeDoc);
        demande.setMotif(motif);
        demande.setAncienNumDoc(txtAncienDoc.getText().trim());
        demande.setNomPere(txtNomPere.getText().trim());
        demande.setNomMere(txtNomMere.getText().trim());
        demande.setNomEpoux(txtNomEpoux.getText().trim());
        demande.setPrenomEpoux(txtPrenomEpoux.getText().trim());
        demande.setLieuMariage(txtLieuMariage.getText().trim());
        demande.setDateNaissance(cmbJourNaissP.getSelectedItem() + "/"
                               + cmbMoisNaissP.getSelectedItem() + "/"
                               + cmbAnneeNaissP.getSelectedItem());
        demande.setDateMariage(cmbJourMariage.getSelectedItem() + "/"
                             + cmbMoisMariage.getSelectedItem() + "/"
                             + cmbAnneeMariage.getSelectedItem());

        // Appeler soumettre() de la classe
        demande.soumettre();
    
        // Mettre à jour le statut
        txtStatut.setText("En attente ");
        txtStatut.setForeground(new java.awt.Color(255, 102, 0));

        
        // Sauvegarder dans le fichier TXT
        sauvegarderPasseport();

    
        // Message de succès
        javax.swing.JOptionPane.showMessageDialog(this,
        "Demande soumise et sauvegardée avec succès !",
        "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    
       
    }//GEN-LAST:event_btnSoumettrePActionPerformed

    private void btnImptimerPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImptimerPActionPerformed
   
        // Vérifier que les champs obligatoires sont remplis
        if (txtNomPasseport.getText().trim().isEmpty() || 
            txtPrenomPasseport.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez remplir les informations avant d'imprimer.",
                "Erreur", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        // Créer l'objet et utiliser genererAccuseReception()
        DemandePasseport demande = new DemandePasseport();
        demande.setNom(txtNomPasseport.getText());
        demande.setPrenom(txtPrenomPasseport.getText());
        demande.setAdresse(txtAdresseP.getText());
        demande.setVille(txtVilleP.getText());
        demande.setTelephone(txtTelP.getText());
        demande.setEmail(txtEmailP.getText());
        demande.setTypeDocument((String) cmbTypeDocument.getSelectedItem());
        demande.setMotif((String) cmbMotifP.getSelectedItem());
        demande.setNomPere(txtNomPere.getText());
        demande.setNomMere(txtNomMere.getText());
        demande.setNomEpoux(txtNomEpoux.getText());
        demande.setPrenomEpoux(txtPrenomEpoux.getText());
        demande.setLieuMariage(txtLieuMariage.getText());
        demande.soumettre();

        // Utiliser l'interface Imprimable
        String contenu = demande.genererAccuseReception();

        javax.swing.JTextArea textArea = new javax.swing.JTextArea(contenu);
        textArea.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 600));

        int choix = javax.swing.JOptionPane.showConfirmDialog(
            this, scrollPane,
            "Aperçu - Accusé de Réception Passeport/CNI",
            javax.swing.JOptionPane.OK_CANCEL_OPTION,
            javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (choix == javax.swing.JOptionPane.OK_OPTION) {
            try {
                textArea.print();
            } catch (java.awt.print.PrinterException e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Erreur impression : " + e.getMessage(),
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Vider tous les champs après succès
        btnAnnulerPActionPerformed(null);  
    }//GEN-LAST:event_btnImptimerPActionPerformed

    private void btnImprimerVisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerVisaActionPerformed
      
        if (txtNom1.getText().trim().isEmpty() || 
        txtPrenom1.getText().trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
            "Veuillez remplir les informations avant d'imprimer.",
            "Erreur", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        DemandeVisa demande = new DemandeVisa();
        demande.setNom(txtNom1.getText());
        demande.setPrenom(txtPrenom1.getText());
        demande.setNumPasseport(txtPasseport1.getText());
        demande.setTypeVisa((String) cmbTypeVisa1.getSelectedItem());
        demande.setNationalite((String) cmbNationnalite1.getSelectedItem());
        demande.setAdresse(txtAdresse1.getText());
        demande.setDateNaissance(cmbJourNaissance.getSelectedItem() + "/"
                               + cmbMoisNaissance.getSelectedItem() + "/"
                               + cmbAnneeNaissance.getSelectedItem());
        demande.setMotifVoyage(txtMotif.getText());
        demande.setCopiePasseport(chkPasseport.isSelected());
        demande.setPhotoIdentite(chkPhoto.isSelected());
        demande.setJustifHebergement(chkHebergement.isSelected());
        demande.setBilletAvion(chkBillet.isSelected());
        demande.soumettre();

        String contenu = demande.genererAccuseReception();
        afficherOptionsImpression(contenu, "Accusé de Réception Visa");
        
        // Vider tous les champs après succès
        btnAnnulerPActionPerformed(null); 
    }//GEN-LAST:event_btnImprimerVisaActionPerformed

    private void btnAnnulerProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnnulerProfilActionPerformed

    private void txtVilleProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVilleProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVilleProfilActionPerformed

    private void txtCPSProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPSProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPSProfilActionPerformed

    private void txtTelephoneProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelephoneProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelephoneProfilActionPerformed

    private void txtNomProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomProfilActionPerformed

    private void txtTotalDossierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalDossierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalDossierActionPerformed

    private void btnMAJProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMAJProfilActionPerformed
        // TODO add your handling code here:
        btnMAJProfil.addActionListener(e -> {
            // Sauvegarder les nouvelles valeurs dans l'objet
            utilisateurConnecte.setTelephone(txtTelephoneProfil.getText());
            utilisateurConnecte.setAdresse(txtAdresseProfil.getText());
            utilisateurConnecte.setVille(txtVilleProfil.getText());
            utilisateurConnecte.setCodePostal(txtCPSProfil.getText());
            utilisateurConnecte.setPays(txtPaysProfil.getText());
    
            // Sauvegarder dans le fichier
            listing.mettreAJourUtilisateur(utilisateurConnecte);
            
            
            javax.swing.JOptionPane.showMessageDialog(this,
            "Profil mis à jour avec succès !",
            "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        });
    }//GEN-LAST:event_btnMAJProfilActionPerformed

    private void txtPaysProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaysProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaysProfilActionPerformed

    private void txtAdresseProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdresseProfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdresseProfilActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(() -> new MenuView().setVisible(true));
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnAnnulerP;
    private javax.swing.JButton btnAnnulerProfil;
    private javax.swing.JButton btnDocuments;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JButton btnImprimerVisa;
    private javax.swing.JButton btnImptimerP;
    private javax.swing.JButton btnMAJProfil;
    private javax.swing.JButton btnPasseport;
    private javax.swing.JButton btnProfil;
    private javax.swing.JButton btnRendezVous;
    private javax.swing.JButton btnSeDeconnecte;
    private javax.swing.JButton btnSeSoumettre;
    private javax.swing.JButton btnSoumettreP;
    private javax.swing.JButton btnTableauBord;
    private javax.swing.JButton btnVisa;
    private javax.swing.JCheckBox chkBillet;
    private javax.swing.JCheckBox chkHebergement;
    private javax.swing.JCheckBox chkPasseport;
    private javax.swing.JCheckBox chkPhoto;
    private javax.swing.JComboBox<String> cmbAnneeDebut;
    private javax.swing.JComboBox<String> cmbAnneeFin;
    private javax.swing.JComboBox<String> cmbAnneeMariage;
    private javax.swing.JComboBox<String> cmbAnneeNaissP;
    private javax.swing.JComboBox<String> cmbAnneeNaissance;
    private javax.swing.JComboBox<String> cmbJourDebut;
    private javax.swing.JComboBox<String> cmbJourFin;
    private javax.swing.JComboBox<String> cmbJourMariage;
    private javax.swing.JComboBox<String> cmbJourNaissP;
    private javax.swing.JComboBox<String> cmbJourNaissance;
    private javax.swing.JComboBox<String> cmbMoisDebut;
    private javax.swing.JComboBox<String> cmbMoisFin;
    private javax.swing.JComboBox<String> cmbMoisMariage;
    private javax.swing.JComboBox<String> cmbMoisNaissP;
    private javax.swing.JComboBox<String> cmbMoisNaissance;
    private javax.swing.JComboBox<String> cmbMotifP;
    private javax.swing.JComboBox<String> cmbNationnalite1;
    private javax.swing.JComboBox<String> cmbNbCopiesMariage;
    private javax.swing.JComboBox<String> cmbNbCopiesNaiss;
    private javax.swing.JComboBox<String> cmbTypeDocument;
    private javax.swing.JComboBox<String> cmbTypeVisa1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Panel panelDocuments;
    private java.awt.Panel panelPasseport;
    private java.awt.Panel panelProfil;
    private java.awt.Panel panelRendezVous;
    private java.awt.Panel panelTableauBord;
    private javax.swing.JTextField txtAdresse1;
    private javax.swing.JTextField txtAdresseP;
    private javax.swing.JTextField txtAdresseProfil;
    private javax.swing.JTextField txtAncienDoc;
    private javax.swing.JTextField txtCPSProfil;
    private javax.swing.JTextField txtDateDelivrance;
    private javax.swing.JTextField txtDossierAccepte;
    private javax.swing.JTextField txtDossierEnCours;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailP;
    private javax.swing.JTextField txtEmailProfil;
    private javax.swing.JTextField txtLieuMariage;
    private javax.swing.JPasswordField txtMotDePasse;
    private javax.swing.JTextArea txtMotif;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtNom1;
    private javax.swing.JTextField txtNomEpoux;
    private javax.swing.JTextField txtNomMere;
    private javax.swing.JLabel txtNomP;
    private javax.swing.JTextField txtNomPasseport;
    private javax.swing.JTextField txtNomPere;
    private javax.swing.JTextField txtNomProfil;
    private javax.swing.JLabel txtNomUser;
    private javax.swing.JTextField txtPasseport1;
    private javax.swing.JTextField txtPaysProfil;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JTextField txtPrenom1;
    private javax.swing.JTextField txtPrenomEpoux;
    private javax.swing.JTextField txtPrenomPasseport;
    private javax.swing.JTextField txtPrenomProfil;
    private javax.swing.JLabel txtSeConnecter;
    private java.awt.Label txtStatut;
    private javax.swing.JLabel txtStatut1;
    private javax.swing.JTextField txtTelP;
    private javax.swing.JTextField txtTelephoneProfil;
    private javax.swing.JTextField txtTotalDossier;
    private javax.swing.JLabel txtTypeDoc;
    private javax.swing.JTextField txtVilleP;
    private javax.swing.JTextField txtVilleProfil;
    // End of variables declaration//GEN-END:variables
}
