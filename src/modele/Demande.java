/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author Utilisateur
 */
public abstract class Demande {
    
    // Attributs communs
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String telephone;
    protected String email;
    protected String statut;
    protected String dateNaissance;
    
     // Constructeur par défaut
    public Demande() {
        this.statut = "En attente";
    }
    
    // Constructeur paramétré
    public Demande(String nom, String prenom, String adresse, 
                   String telephone, String email, String dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.statut = "En attente";
    }
    
    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getAdresse() { return adresse; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public String getStatut() { return statut; }
    public String getDateNaissance() { return dateNaissance; }
    
    // Setters
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setEmail(String email) { this.email = email; }
    public void setStatut(String statut) { this.statut = statut; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }
    
    
     // Méthodes abstraites — OBLIGATOIRES dans les sous-classes
    public abstract void soumettre();
    public abstract String genererResume();
    
    // ToString
    @Override
    public String toString() {
        return "Demande{nom=" + nom + ", prenom=" + prenom + 
               ", statut=" + statut + "}";
    }
    
    
    
}
