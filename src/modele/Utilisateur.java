/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author Utilisateur
 */
public class Utilisateur {
    
    // Attributs
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    
    //Constructeur par défaut
    public Utilisateur() {
    }
    
    // Constructeur paramétré
    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }
    
    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }

    // Setters
    public void setNom(String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setEmail(String email) {this.email = email;}
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse;}
    
    //Méthode de vérification
    public boolean verifierMotDePasse(String motDePasse) {
        return this.motDePasse.equals(motDePasse);
    }
    
    // ToString
    @Override
    public String toString() {
        return "Utilisateur{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", motDePasse=" + motDePasse + '}';
    }
    
    
    
    
    
    
    
    
    
}
