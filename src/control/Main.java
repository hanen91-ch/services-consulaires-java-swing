/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package control;

import io.FichierManager;
import modele.RegistreUtilisateur;
import modele.Utilisateur;
import ui.LoginView;

/**
 *
 * @author Utilisateur
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
          //  logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        RegistreUtilisateur listing = new RegistreUtilisateur();
        // tester la connexion
        //listing.ajouterUtilisateur(new Utilisateur("Tremblay", "Jean", "jean@mail.com", "1234"));
        //listing.ajouterUtilisateur(new Utilisateur("Dupont", "Marie", "marie@mail.com", "abcd"));
        
       
        
        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(() -> new LoginView(listing).setVisible(true));
    
       }
    
}
