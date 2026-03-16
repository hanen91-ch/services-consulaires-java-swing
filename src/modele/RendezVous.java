/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author Utilisateur
 */
public class RendezVous extends Demande implements Imprimable {
    
    private String typeService;
    private String date;
    private String creneau;

    // Constructeur vide
    public RendezVous() {}
    
    // Getters et Setters
    public String getTypeService() { return typeService; }
    public void setTypeService(String typeService) { this.typeService = typeService; }
    
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    
    public String getCreneau() { return creneau; }
    public void setCreneau(String creneau) { this.creneau = creneau; }
    
    // soumettre() — obligatoire car abstraite dans Demande
    @Override
    public void soumettre() {
        this.statut = "En attente";
    }
    
    // genererResume() — obligatoire car abstraite dans Demande
    @Override
    public String genererResume() {
        return "RDV | " + nom + " " + prenom + 
               " | " + typeService + 
               " | " + date + 
               " | " + creneau + 
               " | " + statut;
    }
    
    // genererAccuseReception() — de l'interface Imprimable
    @Override
    public String genererAccuseReception() {
        return "========================================\n" +
               "       ACCUSÉ DE RÉCEPTION\n" +
               "           RENDEZ-VOUS\n" +
               "========================================\n" +
               "Type de Service   : " + typeService + "\n" +
               "--- Informations Personnelles ---\n" +
               "Nom               : " + nom + "\n" +
               "Prénom            : " + prenom + "\n" +
               "Téléphone         : " + telephone + "\n" +
               "Email             : " + email + "\n" +
               "--- Détails Rendez-vous ---\n" +
               "Date              : " + date + "\n" +
               "Créneau           : " + creneau + "\n" +
               "Statut            : " + statut + "\n" +
               "========================================\n";
    }
    
    @Override
    public void imprimer() {
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(genererAccuseReception());
        textArea.setFont(new java.awt.Font("Courier New", java.awt.Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setMargin(new java.awt.Insets(10, 10, 10, 10));

        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 400));

        int choix = javax.swing.JOptionPane.showConfirmDialog(
            null, scrollPane,
            "Aperçu - Accusé de Réception Rendez-vous",
            javax.swing.JOptionPane.OK_CANCEL_OPTION,
            javax.swing.JOptionPane.PLAIN_MESSAGE);

        if (choix == javax.swing.JOptionPane.OK_OPTION) {
            try {
                textArea.print();
            } catch (java.awt.print.PrinterException e) {
                javax.swing.JOptionPane.showMessageDialog(null,
                    "Erreur impression : " + e.getMessage(),
                    "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // toString()
    @Override
    public String toString() {
        return "RendezVous{nom=" + nom + ", prenom=" + prenom +
               ", typeService=" + typeService +
               ", date=" + date +
               ", creneau=" + creneau +
               ", statut=" + statut + "}";
    }
    
}
