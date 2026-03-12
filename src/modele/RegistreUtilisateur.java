/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public class RegistreUtilisateur {
    
    private ArrayList<Utilisateur> registre;
    
    // Constructeur
    public RegistreUtilisateur() {
        registre = new ArrayList<>();
    }
    
    // Getter
    public ArrayList<Utilisateur> getRegistre() {
        return registre;
    }
    
    // Setter
    public void setRegistre(ArrayList<Utilisateur> registre) {
        this.registre = registre;
    }
    
    // méthode pour ajouter un utilisateur
    public void ajouterUtilisateur(Utilisateur u) {
        registre.add(u);
    }
    
    // méthode pour vérifier si un email existe déjà
    public boolean emailExiste(String email) {
        for (Utilisateur u : registre) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    // méthode pour connecter un utilisateur
    public Utilisateur connecter(String email, String motDePasse) {
        for (Utilisateur u : registre) {
            if (u.getEmail().equals(email) && u.verifierMotDePasse(motDePasse)) {
                return u; // trouvé
            }
        }
        return null; // non trouvé
    }
    
    // ToString
    @Override
    public String toString() {
        return "RegistreUtilisateur{" + "registre=" + registre + '}';
    }
    
    
}
