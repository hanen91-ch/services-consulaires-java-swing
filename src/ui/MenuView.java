/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import io.FichierManager;
import javax.swing.JPanel;
import modele.DemandePasseport;
import modele.DemandeVisa;
import modele.RegistreUtilisateur;
import modele.RendezVous;
import modele.Utilisateur;
import utils.Validateur;

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
        btnConfirmerRDV.addActionListener(this::btnConfirmerRDVActionPerformed);
        btnAnnulerRDV.addActionListener(this::btnAnnulerRDVActionPerformed);
        btnImprimerRDV.addActionListener(this::btnImprimerRDVActionPerformed);
        btnImprimerTableau.addActionListener(this::btnImprimerTableauActionPerformed);
        
        jLabel46.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
        choisirPhoto();
    }
});
    
        remplirProfil();
        chargerTableauBord();
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
        
        // charger le table de bord
        private void chargerTableauBord() {
            javax.swing.table.DefaultTableModel model = FichierManager.lireTousFichiers();
            jTable.setModel(model);
            jTable.setRowHeight(25);
            jTable.getTableHeader().setFont(
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
            jTable.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            jTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);

            if (jTable.getColumnCount() > 4) {
                jTable.getColumnModel().getColumn(4).setCellRenderer(
                    new javax.swing.table.DefaultTableCellRenderer() {
                        @Override
                        public java.awt.Component getTableCellRendererComponent(
                            javax.swing.JTable table, Object value, boolean isSelected,
                            boolean hasFocus, int row, int column) {
                            super.getTableCellRendererComponent(
                                table, value, isSelected, hasFocus, row, column);
                            if ("Confirmé".equals(value)) {
                                setForeground(new java.awt.Color(0, 153, 0));
                            } else if ("En attente".equals(value)) {
                                setForeground(new java.awt.Color(255, 102, 0));
                            } else {
                                setForeground(java.awt.Color.BLACK);
                            }
                            return this;
                        }
                    }
                );
            }
            jTable.revalidate();
            jTable.repaint();
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

            if (Validateur.champVide(nom) || Validateur.champVide(prenom) || Validateur.champVide(numPasseport) || Validateur.champVide(adresse)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs obligatoires.",
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!Validateur.comboBoxSelectionne(typeVisa) || !Validateur.comboBoxSelectionne(nationalite)) {
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
   
        // méthode pour remplir ComboBox // demande visa
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
            
            // remplir combo service // panel rendez-vous
            // Type de Service - Rendez-vous
            cmbTypeService.addItem("-- Sélectionner --");
            cmbTypeService.addItem("Demande de Visa");
            cmbTypeService.addItem("Passeport Biométrique");
            cmbTypeService.addItem("Carte Nationale d'Identité (CNI)");
            cmbTypeService.addItem("Légalisation de Documents");
            cmbTypeService.addItem("État Civil");
            cmbTypeService.addItem("Renouvellement Passeport");

            // Jours RDV
            for (int i = 1; i <= 31; i++) {
                cmbJourRDV.addItem(String.format("%02d", i));
            }

            // Mois RDV
            String[] moisRDV = {"Janvier","Février","Mars","Avril",
                                "Mai","Juin","Juillet","Août",
                                "Septembre","Octobre","Novembre","Décembre"};
            for (String m : moisRDV) {
                cmbMoisRDV.addItem(m);
            }

            // Années RDV
            for (int i = 2025; i <= 2030; i++) {
                cmbAnneeRDV.addItem(String.valueOf(i));
            }
            
        }
        

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
        btnProfil = new javax.swing.JButton();
        btnTableauBord = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        panelProfil = new java.awt.Panel();
        jPanel3 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtTotalDossier = new javax.swing.JTextField();
        txtDossierAccepte = new javax.swing.JTextField();
        txtDossierEnCours = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
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
        btnMAJProfil = new javax.swing.JButton();
        btnAnnulerProfil = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        panelRendezVous = new java.awt.Panel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel64 = new javax.swing.JLabel();
        cmbTypeService = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        txtNomRDV = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtPrenomRDV = new javax.swing.JTextField();
        txtTelRDV = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        txtEmailRDV = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        cmbJourRDV = new javax.swing.JComboBox<>();
        cmbMoisRDV = new javax.swing.JComboBox<>();
        cmbAnneeRDV = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        btnH0900 = new javax.swing.JToggleButton();
        btnH0930 = new javax.swing.JToggleButton();
        btnH1000 = new javax.swing.JToggleButton();
        btnH1030 = new javax.swing.JToggleButton();
        btnH1100 = new javax.swing.JToggleButton();
        btnH1130 = new javax.swing.JToggleButton();
        btnH1400 = new javax.swing.JToggleButton();
        btnH1430 = new javax.swing.JToggleButton();
        btnH1500 = new javax.swing.JToggleButton();
        btnH1530 = new javax.swing.JToggleButton();
        btnH1600 = new javax.swing.JToggleButton();
        btnH1630 = new javax.swing.JToggleButton();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        txtStatutRDV = new javax.swing.JLabel();
        btnAnnulerRDV = new javax.swing.JButton();
        btnConfirmerRDV = new javax.swing.JButton();
        btnImprimerRDV = new javax.swing.JButton();
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
        label2 = new java.awt.Label();
        txtStatut = new java.awt.Label();
        btnSoumettreP = new javax.swing.JButton();
        btnImptimerP = new javax.swing.JButton();
        btnAnnulerP = new javax.swing.JButton();
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
        panelTableauBord = new java.awt.Panel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel74 = new javax.swing.JLabel();
        tableauBord = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnImprimerTableau = new javax.swing.JButton();
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
        btnVisa.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        btnVisa.setForeground(new java.awt.Color(255, 255, 255));
        btnVisa.setText("Demandes de Visa");
        btnVisa.addActionListener(this::btnVisaActionPerformed);

        btnPasseport.setBackground(new java.awt.Color(0, 153, 204));
        btnPasseport.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        btnPasseport.setForeground(new java.awt.Color(255, 255, 255));
        btnPasseport.setText("Passeports & CNI");
        btnPasseport.addActionListener(this::btnPasseportActionPerformed);

        btnRendezVous.setBackground(new java.awt.Color(0, 153, 204));
        btnRendezVous.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        btnRendezVous.setForeground(new java.awt.Color(255, 255, 255));
        btnRendezVous.setText("Rendez-vous");
        btnRendezVous.addActionListener(this::btnRendezVousActionPerformed);

        btnProfil.setBackground(new java.awt.Color(0, 153, 204));
        btnProfil.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        btnProfil.setForeground(new java.awt.Color(255, 255, 255));
        btnProfil.setText("Mon Profil");
        btnProfil.addActionListener(this::btnProfilActionPerformed);

        btnTableauBord.setBackground(new java.awt.Color(0, 153, 204));
        btnTableauBord.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
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
                    .addComponent(btnProfil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTableauBord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btnTableauBord, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(79, 79, 79)
                .addComponent(btnVisa, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnPasseport, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnRendezVous, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(85, 85, 85)
                .addComponent(btnProfil, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addGap(82, 82, 82))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new java.awt.CardLayout());

        panelProfil.setBackground(new java.awt.Color(204, 204, 204));

        jLabel48.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel48.setText("Dossiers : ");

        jLabel59.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel59.setText("Acceptés :");

        jLabel60.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel60.setText("En Cours :");

        txtTotalDossier.addActionListener(this::txtTotalDossierActionPerformed);

        jLabel46.setText("Telecharger votre photo ici");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotalDossier)
                    .addComponent(txtDossierAccepte)
                    .addComponent(txtDossierEnCours)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTotalDossier, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDossierAccepte, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDossierEnCours, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel46.getAccessibleContext().setAccessibleName("");

        jPanel4.setPreferredSize(new java.awt.Dimension(851, 400));

        jLabel49.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel49.setText("Nom : ");

        jLabel50.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel50.setText("Prénom : ");

        txtNomProfil.addActionListener(this::txtNomProfilActionPerformed);

        jLabel51.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel51.setText("Téléphone :");

        txtTelephoneProfil.addActionListener(this::txtTelephoneProfilActionPerformed);

        jLabel52.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel52.setText("Email : ");

        jLabel53.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel53.setText("Adresse :");

        txtAdresseProfil.addActionListener(this::txtAdresseProfilActionPerformed);

        jLabel54.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel54.setText("Pays :");

        txtPaysProfil.addActionListener(this::txtPaysProfilActionPerformed);

        jLabel55.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel55.setText("Code Postal : ");

        txtCPSProfil.addActionListener(this::txtCPSProfilActionPerformed);

        jLabel56.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel56.setText("Ville : ");

        txtVilleProfil.addActionListener(this::txtVilleProfilActionPerformed);

        btnMAJProfil.setBackground(new java.awt.Color(0, 102, 153));
        btnMAJProfil.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnMAJProfil.setForeground(new java.awt.Color(255, 255, 255));
        btnMAJProfil.setText("Mettre a jour");
        btnMAJProfil.addActionListener(this::btnMAJProfilActionPerformed);

        btnAnnulerProfil.setBackground(new java.awt.Color(0, 102, 153));
        btnAnnulerProfil.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnAnnulerProfil.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnulerProfil.setText("Annuler");
        btnAnnulerProfil.addActionListener(this::btnAnnulerProfilActionPerformed);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCPSProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtPaysProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(223, 223, 223)))
                            .addComponent(txtAdresseProfil, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelephoneProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrenomProfil)
                            .addComponent(txtNomProfil)
                            .addComponent(txtEmailProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(481, 481, 481)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVilleProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMAJProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(171, 171, 171)
                        .addComponent(btnAnnulerProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(211, 211, 211))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrenomProfil)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelephoneProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmailProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAdresseProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVilleProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPaysProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCPSProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMAJProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnnulerProfil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jLabel57.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        jLabel57.setText("Services Consulaires");

        jLabel58.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel58.setText("MON PROFIL");

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator5.setDoubleBuffered(true);

        javax.swing.GroupLayout panelProfilLayout = new javax.swing.GroupLayout(panelProfil);
        panelProfil.setLayout(panelProfilLayout);
        panelProfilLayout.setHorizontalGroup(
            panelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProfilLayout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelProfilLayout.createSequentialGroup()
                .addGap(442, 442, 442)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelProfilLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelProfilLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelProfilLayout.setVerticalGroup(
            panelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProfilLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel57)
                .addGap(6, 6, 6)
                .addComponent(jLabel58)
                .addGap(12, 12, 12)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(panelProfilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel2.add(panelProfil, "card7");

        panelRendezVous.setBackground(new java.awt.Color(204, 204, 204));

        jLabel62.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        jLabel62.setText("Services Consulaires");

        jLabel63.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel63.setText("DEMANDE RENDEZ-VOUS");

        jSeparator6.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator6.setDoubleBuffered(true);

        jLabel64.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel64.setText("Type de Service :");
        jLabel64.setPreferredSize(new java.awt.Dimension(115, 25));

        cmbTypeService.setPreferredSize(new java.awt.Dimension(72, 25));
        cmbTypeService.addActionListener(this::cmbTypeServiceActionPerformed);

        txtNomRDV.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel65.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 14)); // NOI18N
        jLabel65.setText("Nom :");

        jLabel66.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 14)); // NOI18N
        jLabel66.setText("Prénom :");

        txtPrenomRDV.setPreferredSize(new java.awt.Dimension(73, 25));

        txtTelRDV.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel67.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 14)); // NOI18N
        jLabel67.setText("Téléphone : ");
        jLabel67.setPreferredSize(new java.awt.Dimension(65, 25));

        jLabel68.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 14)); // NOI18N
        jLabel68.setText("Email : ");
        jLabel68.setPreferredSize(new java.awt.Dimension(38, 25));

        txtEmailRDV.setPreferredSize(new java.awt.Dimension(73, 25));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTelRDV, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addComponent(txtNomRDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrenomRDV, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(txtEmailRDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 28, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrenomRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmailRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel69.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel69.setText("Informations Personnelles :");

        jLabel70.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel70.setText("Choisir une Date :");
        jLabel70.setPreferredSize(new java.awt.Dimension(93, 25));
        jLabel70.setRequestFocusEnabled(false);

        btnH0900.setText("09:00");

        btnH0930.setText("09:30");

        btnH1000.setText("10:00");

        btnH1030.setText("10:30");

        btnH1100.setText("11:00");

        btnH1130.setText("11:30");

        btnH1400.setText("14:00");

        btnH1430.setText("14:30");

        btnH1500.setText("15:00");

        btnH1530.setText("15:30");

        btnH1600.setText("16:00");

        btnH1630.setText("16:30");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(156, 156, 156)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnH0900, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnH0930, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnH1000, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnH1130, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnH1100, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnH1030, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnH1430, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnH1400, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnH1500, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnH1600, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnH1530, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnH1630, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnH0900)
                    .addComponent(btnH1030)
                    .addComponent(btnH1400)
                    .addComponent(btnH1530))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnH0930)
                    .addComponent(btnH1100)
                    .addComponent(btnH1430)
                    .addComponent(btnH1600))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnH1000)
                    .addComponent(btnH1130)
                    .addComponent(btnH1500)
                    .addComponent(btnH1630))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel71.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        jLabel71.setText("Créneaux disponibles :");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel72.setText("Statut :");

        txtStatutRDV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtStatutRDV.setPreferredSize(new java.awt.Dimension(45, 25));

        btnAnnulerRDV.setBackground(new java.awt.Color(0, 102, 153));
        btnAnnulerRDV.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnAnnulerRDV.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnulerRDV.setText("Annuler");
        btnAnnulerRDV.addActionListener(this::btnAnnulerRDVActionPerformed);

        btnConfirmerRDV.setBackground(new java.awt.Color(0, 102, 153));
        btnConfirmerRDV.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnConfirmerRDV.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmerRDV.setText("Confirmer le rendez-vous");
        btnConfirmerRDV.addActionListener(this::btnConfirmerRDVActionPerformed);

        btnImprimerRDV.setBackground(new java.awt.Color(0, 102, 153));
        btnImprimerRDV.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnImprimerRDV.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimerRDV.setText("Imprimer Fiche RDV");
        btnImprimerRDV.setOpaque(true);
        btnImprimerRDV.setPreferredSize(new java.awt.Dimension(88, 36));
        btnImprimerRDV.addActionListener(this::btnImprimerRDVActionPerformed);

        javax.swing.GroupLayout panelRendezVousLayout = new javax.swing.GroupLayout(panelRendezVous);
        panelRendezVous.setLayout(panelRendezVousLayout);
        panelRendezVousLayout.setHorizontalGroup(
            panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRendezVousLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(300, 300, 300))
            .addGroup(panelRendezVousLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRendezVousLayout.createSequentialGroup()
                        .addComponent(jSeparator6)
                        .addContainerGap())
                    .addGroup(panelRendezVousLayout.createSequentialGroup()
                        .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel71)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelRendezVousLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(cmbTypeService, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelRendezVousLayout.createSequentialGroup()
                                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbJourRDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbMoisRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAnneeRDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRendezVousLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStatutRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))
                            .addGroup(panelRendezVousLayout.createSequentialGroup()
                                .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImprimerRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAnnulerRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnConfirmerRDV))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        panelRendezVousLayout.setVerticalGroup(
            panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRendezVousLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTypeService, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRendezVousLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbJourRDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMoisRDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAnneeRDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRendezVousLayout.createSequentialGroup()
                        .addComponent(btnConfirmerRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)))
                .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRendezVousLayout.createSequentialGroup()
                        .addComponent(btnImprimerRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(btnAnnulerRDV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(89, 89, 89)
                        .addGroup(panelRendezVousLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel72)
                            .addComponent(txtStatutRDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRendezVousLayout.createSequentialGroup()
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelRendezVous, "card4");

        panelPasseport.setBackground(new java.awt.Color(204, 204, 204));
        panelPasseport.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panelPasseport.setMaximumSize(new java.awt.Dimension(0, 0));
        panelPasseport.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel24.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel24.setText("FORMULAIRE DEMANDE PASSEPORT & CNI");

        jLabel25.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
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
        txtVilleP.addActionListener(this::txtVillePActionPerformed);

        txtPrenomPasseport.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel29.setText("Date de Naissance :");

        cmbJourNaissP.setPreferredSize(new java.awt.Dimension(72, 25));
        cmbJourNaissP.addActionListener(this::cmbJourNaissPActionPerformed);

        jLabel34.setText("Téléphone :");

        cmbMoisNaissP.setMinimumSize(new java.awt.Dimension(72, 25));
        cmbMoisNaissP.setPreferredSize(new java.awt.Dimension(72, 25));

        txtTelP.setMinimumSize(new java.awt.Dimension(64, 25));
        txtTelP.setPreferredSize(new java.awt.Dimension(64, 25));
        txtTelP.addActionListener(this::txtTelPActionPerformed);

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
        cmbMoisMariage.addActionListener(this::cmbMoisMariageActionPerformed);

        cmbAnneeMariage.setPreferredSize(new java.awt.Dimension(72, 25));

        cmbNbCopiesMariage.setPreferredSize(new java.awt.Dimension(72, 25));
        cmbNbCopiesMariage.addActionListener(this::cmbNbCopiesMariageActionPerformed);

        jLabel45.setText("Nombre de Copies :");

        label2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        label2.setText("Statut : ");

        txtStatut.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        btnSoumettreP.setBackground(new java.awt.Color(0, 102, 153));
        btnSoumettreP.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnSoumettreP.setForeground(new java.awt.Color(255, 255, 255));
        btnSoumettreP.setLabel("Soumettre la demande");
        btnSoumettreP.addActionListener(this::btnSoumettrePActionPerformed);

        btnImptimerP.setBackground(new java.awt.Color(0, 102, 153));
        btnImptimerP.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnImptimerP.setForeground(new java.awt.Color(255, 255, 255));
        btnImptimerP.setText("Imprimer");
        btnImptimerP.addActionListener(this::btnImptimerPActionPerformed);

        btnAnnulerP.setBackground(new java.awt.Color(0, 102, 153));
        btnAnnulerP.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnAnnulerP.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnulerP.setText("Annuler");
        btnAnnulerP.addActionListener(this::btnAnnulerPActionPerformed);

        javax.swing.GroupLayout panelPasseportLayout = new javax.swing.GroupLayout(panelPasseport);
        panelPasseport.setLayout(panelPasseportLayout);
        panelPasseportLayout.setHorizontalGroup(
            panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPasseportLayout.createSequentialGroup()
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtTypeDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTypeDocument, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNomP, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPasseportLayout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelPasseportLayout.createSequentialGroup()
                                    .addComponent(cmbJourNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbMoisNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(cmbAnneeNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtAdresseP, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(142, 142, 142)
                        .addComponent(txtVilleP, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPasseportLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNomEpoux, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(panelPasseportLayout.createSequentialGroup()
                                                        .addComponent(jLabel45)
                                                        .addGap(22, 22, 22))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPasseportLayout.createSequentialGroup()
                                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)))
                                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cmbNbCopiesMariage, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(panelPasseportLayout.createSequentialGroup()
                                                        .addComponent(cmbJourMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cmbMoisMariage, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(cmbAnneeMariage, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGap(57, 57, 57)
                                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPasseportLayout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(txtStatut, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelPasseportLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPrenomEpoux, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                                            .addComponent(txtLieuMariage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(txtNomPasseport, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelPasseportLayout.createSequentialGroup()
                                        .addComponent(jLabel40)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbNbCopiesNaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(4, 4, 4)
                                            .addComponent(txtNomPere, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelPasseportLayout.createSequentialGroup()
                                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(65, 65, 65)
                                            .addComponent(cmbMotifP, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(57, 57, 57)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrenomPasseport, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(txtEmailP, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelPasseportLayout.createSequentialGroup()
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtNomMere, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelPasseportLayout.createSequentialGroup()
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtAncienDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPasseportLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSoumettreP)
                .addGap(18, 18, 18)
                .addComponent(btnImptimerP, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAnnulerP, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        panelPasseportLayout.setVerticalGroup(
            panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPasseportLayout.createSequentialGroup()
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel25)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel24)
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtTypeDoc))
                            .addComponent(cmbTypeDocument, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomP))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGap(186, 186, 186)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomPasseport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPrenomPasseport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbAnneeNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMoisNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbJourNaissP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29))))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtVilleP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtAdresseP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEmailP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTelP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMotifP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(txtAncienDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomPere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomMere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbNbCopiesNaiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNomEpoux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrenomEpoux, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbAnneeMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLieuMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPasseportLayout.createSequentialGroup()
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbNbCopiesMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPasseportLayout.createSequentialGroup()
                                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStatut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)))
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSoumettreP)
                            .addComponent(btnImptimerP)
                            .addComponent(btnAnnulerP))
                        .addGap(65, 65, 65))
                    .addGroup(panelPasseportLayout.createSequentialGroup()
                        .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbJourMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel44))
                            .addComponent(cmbMoisMariage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(panelPasseportLayout.createSequentialGroup()
                .addGap(480, 480, 480)
                .addGroup(panelPasseportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(163, 163, 163))
        );

        jPanel2.add(panelPasseport, "card3");

        panelVisa.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        jLabel8.setText("Services Consulaires");

        jLabel10.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel10.setText("FORMULAIRE DEMANDE VISA");

        jLabel12.setText("Nom :");

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setDoubleBuffered(true);

        txtNom1.setPreferredSize(new java.awt.Dimension(73, 25));

        jLabel13.setText("Prénom :");

        txtPrenom1.setPreferredSize(new java.awt.Dimension(73, 25));
        txtPrenom1.addActionListener(this::txtPrenom1ActionPerformed);

        jLabel14.setText("Numer de Passeport :");
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

        btnAnnuler.setBackground(new java.awt.Color(0, 102, 153));
        btnAnnuler.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnAnnuler.setForeground(new java.awt.Color(255, 255, 255));
        btnAnnuler.setText("Annuler");

        btnSeSoumettre.setBackground(new java.awt.Color(0, 102, 153));
        btnSeSoumettre.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnSeSoumettre.setForeground(new java.awt.Color(255, 255, 255));
        btnSeSoumettre.setText("Soumettre");
        btnSeSoumettre.addActionListener(this::btnSeSoumettreActionPerformed);

        btnImprimerVisa.setBackground(new java.awt.Color(0, 102, 153));
        btnImprimerVisa.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnImprimerVisa.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimerVisa.setText("Imprimer");
        btnImprimerVisa.addActionListener(this::btnImprimerVisaActionPerformed);

        javax.swing.GroupLayout panelVisaLayout = new javax.swing.GroupLayout(panelVisa);
        panelVisa.setLayout(panelVisaLayout);
        panelVisaLayout.setHorizontalGroup(
            panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisaLayout.createSequentialGroup()
                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelVisaLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisaLayout.createSequentialGroup()
                                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbJourDebut, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMoisDebut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAnneeDebut, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbTypeVisa1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtPasseport1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(panelVisaLayout.createSequentialGroup()
                                                .addComponent(cmbJourNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cmbMoisNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cmbAnneeNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14)
                                        .addComponent(cmbJourFin, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMoisFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAnneeFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(41, 41, 41)
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtAdresse1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPrenom1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelVisaLayout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(cmbNationnalite1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisaLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtDateDelivrance, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(188, 188, 188))
                            .addGroup(panelVisaLayout.createSequentialGroup()
                                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkBillet)
                                            .addComponent(chkPasseport))
                                        .addGap(135, 135, 135)
                                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(chkPhoto)
                                            .addComponent(chkHebergement)))
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 534, Short.MAX_VALUE))
                            .addGroup(panelVisaLayout.createSequentialGroup()
                                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelVisaLayout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(txtStatut1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnSeSoumettre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(btnImprimerVisa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(59, 59, 59)
                                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
            .addGroup(panelVisaLayout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVisaLayout.setVerticalGroup(
            panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisaLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDateDelivrance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbNationnalite1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbJourFin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMoisFin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAnneeFin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(33, 33, 33)
                .addComponent(jLabel21)
                .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkBillet)
                            .addComponent(chkHebergement))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkPasseport)
                            .addComponent(chkPhoto))
                        .addGap(31, 31, 31)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtStatut1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelVisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnImprimerVisa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSeSoumettre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))))
        );

        jPanel2.add(panelVisa, "panelVisa");

        panelTableauBord.setBackground(new java.awt.Color(204, 204, 204));
        panelTableauBord.setEnabled(false);
        panelTableauBord.setPreferredSize(new java.awt.Dimension(1097, 618));

        jLabel73.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 48)); // NOI18N
        jLabel73.setText("Services Consulaires");

        jSeparator7.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator7.setDoubleBuffered(true);

        jLabel74.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 24)); // NOI18N
        jLabel74.setText("MON TABLEAU DE BORD");

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableauBord.setViewportView(jTable);

        btnImprimerTableau.setBackground(new java.awt.Color(0, 102, 153));
        btnImprimerTableau.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnImprimerTableau.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimerTableau.setText("Imprimer");
        btnImprimerTableau.setPreferredSize(new java.awt.Dimension(88, 36));
        btnImprimerTableau.addActionListener(this::btnImprimerTableauActionPerformed);

        btnSeDeconnecte.setBackground(new java.awt.Color(0, 102, 153));
        btnSeDeconnecte.setFont(new java.awt.Font("PMingLiU-ExtB", 1, 18)); // NOI18N
        btnSeDeconnecte.setForeground(new java.awt.Color(255, 255, 255));
        btnSeDeconnecte.setText("Se déconnecter");
        btnSeDeconnecte.addActionListener(this::btnSeDeconnecteActionPerformed);

        javax.swing.GroupLayout panelTableauBordLayout = new javax.swing.GroupLayout(panelTableauBord);
        panelTableauBord.setLayout(panelTableauBordLayout);
        panelTableauBordLayout.setHorizontalGroup(
            panelTableauBordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTableauBordLayout.createSequentialGroup()
                .addGroup(panelTableauBordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTableauBordLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(292, 292, 292))
                    .addGroup(panelTableauBordLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelTableauBordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelTableauBordLayout.createSequentialGroup()
                                .addComponent(btnImprimerTableau, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSeDeconnecte, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tableauBord, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)))
                .addGap(134, 134, 134))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableauBordLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel73)
                .addGap(354, 354, 354))
        );
        panelTableauBordLayout.setVerticalGroup(
            panelTableauBordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTableauBordLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTableauBordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTableauBordLayout.createSequentialGroup()
                        .addComponent(jSeparator7, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                        .addGap(513, 513, 513))
                    .addGroup(panelTableauBordLayout.createSequentialGroup()
                        .addComponent(tableauBord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTableauBordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSeDeconnecte, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImprimerTableau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );

        jPanel2.add(panelTableauBord, "card5");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Connecté en tant que :");

        txtNomUser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomUser, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNomUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeDeconnecteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeDeconnecteActionPerformed
        // TODO add your handling code here:
        new LoginView(listing).setVisible(true); // ouvre la fenetre de connecxion
        this.dispose(); // ferme la fenetre de menu
        
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
        
        // Remplir automatiquement depuis le profil
        txtNomRDV.setText(utilisateurConnecte.getNom());
        txtPrenomRDV.setText(utilisateurConnecte.getPrenom());
        txtTelRDV.setText(utilisateurConnecte.getTelephone());
        txtEmailRDV.setText(utilisateurConnecte.getEmail());
        
    }//GEN-LAST:event_btnRendezVousActionPerformed

    private void btnTableauBordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTableauBordActionPerformed
        // TODO add your handling code here:
        // Afficher le panel tableau de bord
        cardLayout.show(jPanel2, "card5");
        jPanel2.revalidate();
        jPanel2.repaint();
        chargerTableauBord();
    }//GEN-LAST:event_btnTableauBordActionPerformed

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
            if (Validateur.champVide(nom) || Validateur.champVide(prenom) || Validateur.champVide(email) || Validateur.champVide(motDePasse)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs.",
                    "Erreur",
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Vérifier le format de l'email
            if (!Validateur.emailValide(email)) {
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Veuillez entrer un email valide.",
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
        if (Validateur.champVide(nom) || Validateur.champVide(prenom) || Validateur.champVide(numPasseport) || Validateur.champVide(adresse)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez remplir tous les champs obligatoires.",
                "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Validateur.comboBoxSelectionne(typeVisa) || !Validateur.comboBoxSelectionne(nationalite)) {
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
        FichierManager.sauvegarderVisa(demande);

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
        if (Validateur.champVide(nom) || Validateur.champVide(prenom) || Validateur.champVide(adresse)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez remplir les champs obligatoires (Nom, Prénom, Adresse).",
                "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Validateur.comboBoxSelectionne(typeDoc)) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Veuillez sélectionner un Type de Document.",
                "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!Validateur.comboBoxSelectionne(motif)) {
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
        FichierManager.sauvegarderPasseport(demande);

    
        // Message de succès
        javax.swing.JOptionPane.showMessageDialog(this,
        "Demande soumise et sauvegardée avec succès !",
        "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    
       
    }//GEN-LAST:event_btnSoumettrePActionPerformed

    private void btnImptimerPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImptimerPActionPerformed
   
        // Vérifier que les champs obligatoires sont remplis
        if (Validateur.champVide(txtNomPasseport.getText()) || Validateur.champVide(txtPrenomPasseport.getText())) {
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
      
        if (Validateur.champVide(txtNom1.getText()) || Validateur.champVide(txtPrenom1.getText())) {
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

    private void cmbTypeServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTypeServiceActionPerformed

    private void btnConfirmerRDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmerRDVActionPerformed
        
    // Récupérer les valeurs
    String nom = txtNomRDV.getText();
    String prenom = txtPrenomRDV.getText();
    String tel = txtTelRDV.getText();
    String email = txtEmailRDV.getText();
    String typeService = (String) cmbTypeService.getSelectedItem();
    String jour = (String) cmbJourRDV.getSelectedItem();
    String mois = (String) cmbMoisRDV.getSelectedItem();
    String annee = (String) cmbAnneeRDV.getSelectedItem();
    
    // Trouver le créneau sélectionné
    String creneau = "";
    javax.swing.JToggleButton[] boutons = {
        btnH0900, btnH0930, btnH1000, btnH1030,
        btnH1100, btnH1130, btnH1400, btnH1430,
        btnH1500, btnH1530, btnH1600, btnH1630
    };
    for (javax.swing.JToggleButton btn : boutons) {
        if (btn.isSelected()) {
            creneau = btn.getText();
            break;
        }
    }
    
    // Validation des champs obligatoires
    if (Validateur.champVide(nom) || Validateur.champVide(prenom)) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Veuillez remplir le Nom et le Prénom.",
            "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!Validateur.comboBoxSelectionne(typeService)) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Veuillez sélectionner un Type de Service.",
            "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!Validateur.dateValide(jour, mois, annee)) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Veuillez choisir une date.",
            "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (!Validateur.creneauSelectionne(creneau)) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Veuillez sélectionner un créneau horaire.",
            "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Mettre à jour le statut
    txtStatutRDV.setText("Confirmé");
    txtStatutRDV.setForeground(new java.awt.Color(0, 153, 0));
    
    // Créer l'objet RendezVous et sauvegarder  ← AJOUTEZ ICI
    RendezVous rdv = new RendezVous();
    rdv.setNom(nom);
    rdv.setPrenom(prenom);
    rdv.setTelephone(tel);
    rdv.setEmail(email);
    rdv.setTypeService(typeService);
    rdv.setDate(jour + " " + mois + " " + annee);
    rdv.setCreneau(creneau);
    rdv.soumettre();
    FichierManager.sauvegarderRDV(rdv);  // ← AJOUTEZ ICI
    
    
    
    // Message de confirmation
    javax.swing.JOptionPane.showMessageDialog(this,
        "Rendez-vous confirmé !\n\n" +
        "Nom       : " + nom + " " + prenom + "\n" +
        "Service   : " + typeService + "\n" +
        "Date      : " + jour + " " + mois + " " + annee + "\n" +
        "Heure     : " + creneau,
        "Confirmation", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnConfirmerRDVActionPerformed

    private void btnAnnulerRDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerRDVActionPerformed
        // TODO add your handling code here:
        txtNomRDV.setText("");
        txtPrenomRDV.setText("");
        txtTelRDV.setText("");
        txtEmailRDV.setText("");
    
    cmbTypeService.setSelectedIndex(0);
    cmbJourRDV.setSelectedIndex(0);
    cmbMoisRDV.setSelectedIndex(0);
    cmbAnneeRDV.setSelectedIndex(0);
    
    javax.swing.JToggleButton[] boutons = {
        btnH0900, btnH0930, btnH1000, btnH1030,
        btnH1100, btnH1130, btnH1400, btnH1430,
        btnH1500, btnH1530, btnH1600, btnH1630
    };
    for (javax.swing.JToggleButton btn : boutons) {
        btn.setSelected(false);
    }
    
    txtStatutRDV.setText("");
    }//GEN-LAST:event_btnAnnulerRDVActionPerformed

    private void btnImprimerRDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerRDVActionPerformed
        // TODO add your handling code here:
        // Vérifier que les champs obligatoires sont remplis
    if (Validateur.champVide(txtNomRDV.getText()) || Validateur.champVide(txtPrenomRDV.getText())) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Veuillez remplir les informations avant d'imprimer.",
            "Erreur", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Vérifier qu'un créneau est sélectionné
    String creneau = "";
    javax.swing.JToggleButton[] boutons = {
        btnH0900, btnH0930, btnH1000, btnH1030,
        btnH1100, btnH1130, btnH1400, btnH1430,
        btnH1500, btnH1530, btnH1600, btnH1630
    };
    for (javax.swing.JToggleButton btn : boutons) {
        if (btn.isSelected()) {
            creneau = btn.getText();
            break;
        }
    }
    
    if (!Validateur.creneauSelectionne(creneau)) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Veuillez sélectionner un créneau horaire.",
            "Erreur", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Créer l'objet RendezVous
    RendezVous rdv = new RendezVous();
    rdv.setNom(txtNomRDV.getText());
    rdv.setPrenom(txtPrenomRDV.getText());
    rdv.setTelephone(txtTelRDV.getText());
    rdv.setEmail(txtEmailRDV.getText());
    rdv.setTypeService((String) cmbTypeService.getSelectedItem());
    rdv.setDate(cmbJourRDV.getSelectedItem() + " "
              + cmbMoisRDV.getSelectedItem() + " "
              + cmbAnneeRDV.getSelectedItem());
    rdv.setCreneau(creneau);
    rdv.soumettre();
    
    // Utiliser la méthode genererAccuseReception()
    String contenu = rdv.genererAccuseReception();
    afficherOptionsImpression(contenu, "Accusé de Réception Rendez-vous");
    }//GEN-LAST:event_btnImprimerRDVActionPerformed

    private void btnImprimerTableauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimerTableauActionPerformed
        // TODO add your handling code here:
    
    // Vérifier que le tableau a des données
    if (jTable.getRowCount() == 0) {
        javax.swing.JOptionPane.showMessageDialog(this,
            "Le tableau est vide, rien à imprimer.",
            "Erreur", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Construire le contenu
    StringBuilder contenu = new StringBuilder();
    contenu.append("========================================\n");
    contenu.append("    TABLEAU DE BORD - RÉCAPITULATIF\n");
    contenu.append("========================================\n");
    contenu.append(String.format("%-15s %-15s %-15s %-20s %-15s%n",
        "Type", "Nom", "Prénom", "Détails", "Statut"));
    contenu.append("----------------------------------------\n");
    
    for (int i = 0; i < jTable.getRowCount(); i++) {
        String type    = jTable.getValueAt(i, 0) != null ? jTable.getValueAt(i, 0).toString() : "";
        String nom     = jTable.getValueAt(i, 1) != null ? jTable.getValueAt(i, 1).toString() : "";
        String prenom  = jTable.getValueAt(i, 2) != null ? jTable.getValueAt(i, 2).toString() : "";
        String details = jTable.getValueAt(i, 3) != null ? jTable.getValueAt(i, 3).toString() : "";
        String statut  = jTable.getValueAt(i, 4) != null ? jTable.getValueAt(i, 4).toString() : "";
        contenu.append(String.format("%-15s %-15s %-15s %-20s %-15s%n",
            type, nom, prenom, details, statut));
    }
    contenu.append("========================================\n");
    contenu.append("Total : " + jTable.getRowCount() + " demande(s)\n");
    contenu.append("========================================\n");
    
    // Afficher aperçu
    javax.swing.JTextArea textArea = new javax.swing.JTextArea(contenu.toString());
    textArea.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12));
    textArea.setEditable(false);
    textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));
    
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
    scrollPane.setPreferredSize(new java.awt.Dimension(600, 400));
    
    // Boutons options
    Object[] options = {"Imprimer", "Sauvegarder en TXT", "Annuler"};
    
    int choix = javax.swing.JOptionPane.showOptionDialog(
        this, scrollPane,
        "Aperçu - Tableau de Bord",
        javax.swing.JOptionPane.DEFAULT_OPTION,
        javax.swing.JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
    
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
        fc.setSelectedFile(new java.io.File("tableau_bord.txt"));
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("TXT", "txt"));
        if (fc.showSaveDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
            try (java.io.FileWriter fw = new java.io.FileWriter(fc.getSelectedFile())) {
                fw.write(contenu.toString());
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Fichier TXT sauvegardé avec succès !",
                    "Succès", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } catch (java.io.IOException e) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Erreur sauvegarde : " + e.getMessage(),
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
        
    }//GEN-LAST:event_btnImprimerTableauActionPerformed

    private void cmbMoisMariageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMoisMariageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMoisMariageActionPerformed

    private void txtTelPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelPActionPerformed

    private void txtVillePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVillePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVillePActionPerformed

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
    private javax.swing.JButton btnAnnulerRDV;
    private javax.swing.JButton btnConfirmerRDV;
    private javax.swing.JButton btnEnregistrer;
    private javax.swing.JToggleButton btnH0900;
    private javax.swing.JToggleButton btnH0930;
    private javax.swing.JToggleButton btnH1000;
    private javax.swing.JToggleButton btnH1030;
    private javax.swing.JToggleButton btnH1100;
    private javax.swing.JToggleButton btnH1130;
    private javax.swing.JToggleButton btnH1400;
    private javax.swing.JToggleButton btnH1430;
    private javax.swing.JToggleButton btnH1500;
    private javax.swing.JToggleButton btnH1530;
    private javax.swing.JToggleButton btnH1600;
    private javax.swing.JToggleButton btnH1630;
    private javax.swing.JButton btnImprimerRDV;
    private javax.swing.JButton btnImprimerTableau;
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
    private javax.swing.JComboBox<String> cmbAnneeRDV;
    private javax.swing.JComboBox<String> cmbJourDebut;
    private javax.swing.JComboBox<String> cmbJourFin;
    private javax.swing.JComboBox<String> cmbJourMariage;
    private javax.swing.JComboBox<String> cmbJourNaissP;
    private javax.swing.JComboBox<String> cmbJourNaissance;
    private javax.swing.JComboBox<String> cmbJourRDV;
    private javax.swing.JComboBox<String> cmbMoisDebut;
    private javax.swing.JComboBox<String> cmbMoisFin;
    private javax.swing.JComboBox<String> cmbMoisMariage;
    private javax.swing.JComboBox<String> cmbMoisNaissP;
    private javax.swing.JComboBox<String> cmbMoisNaissance;
    private javax.swing.JComboBox<String> cmbMoisRDV;
    private javax.swing.JComboBox<String> cmbMotifP;
    private javax.swing.JComboBox<String> cmbNationnalite1;
    private javax.swing.JComboBox<String> cmbNbCopiesMariage;
    private javax.swing.JComboBox<String> cmbNbCopiesNaiss;
    private javax.swing.JComboBox<String> cmbTypeDocument;
    private javax.swing.JComboBox<String> cmbTypeService;
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
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable;
    private java.awt.Label label2;
    private java.awt.Panel panelPasseport;
    private java.awt.Panel panelProfil;
    private java.awt.Panel panelRendezVous;
    private java.awt.Panel panelTableauBord;
    private javax.swing.JScrollPane tableauBord;
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
    private javax.swing.JTextField txtEmailRDV;
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
    private javax.swing.JTextField txtNomRDV;
    private javax.swing.JLabel txtNomUser;
    private javax.swing.JTextField txtPasseport1;
    private javax.swing.JTextField txtPaysProfil;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JTextField txtPrenom1;
    private javax.swing.JTextField txtPrenomEpoux;
    private javax.swing.JTextField txtPrenomPasseport;
    private javax.swing.JTextField txtPrenomProfil;
    private javax.swing.JTextField txtPrenomRDV;
    private javax.swing.JLabel txtSeConnecter;
    private java.awt.Label txtStatut;
    private javax.swing.JLabel txtStatut1;
    private javax.swing.JLabel txtStatutRDV;
    private javax.swing.JTextField txtTelP;
    private javax.swing.JTextField txtTelRDV;
    private javax.swing.JTextField txtTelephoneProfil;
    private javax.swing.JTextField txtTotalDossier;
    private javax.swing.JLabel txtTypeDoc;
    private javax.swing.JTextField txtVilleP;
    private javax.swing.JTextField txtVilleProfil;
    // End of variables declaration//GEN-END:variables
}
