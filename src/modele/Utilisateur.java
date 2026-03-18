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
    private String telephone;
    private String adresse;
    private String ville;
    private String codePostal;
    private String pays;
    private String cheminPhoto;
    
    //Constructeur par défaut
    public Utilisateur() {
    }
    
    // Constructeur paramétré
    public Utilisateur(String nom, String prenom, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = "";
        this.adresse = "";
        this.ville = "";
        this.codePostal = "";
        this.pays = "";
    }
    
    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMotDePasse() { return motDePasse; }
    public String getTelephone() { return telephone; }
    public String getAdresse() { return adresse; }
    public String getVille() { return ville; }
    public String getCodePostal() { return codePostal; }
    public String getPays() { return pays; }
    public String getCheminPhoto() { return cheminPhoto; }
    
    // Setters
    public void setNom(String nom) {this.nom = nom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    public void setEmail(String email) {this.email = email;}
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse;}
     public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setVille(String ville) { this.ville = ville; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    public void setPays(String pays) { this.pays = pays; }
    public void setCheminPhoto(String cheminPhoto) { this.cheminPhoto = cheminPhoto; }
    
    //Méthode de vérification
    public boolean verifierMotDePasse(String motDePasse) {
        return this.motDePasse.equals(motDePasse);
    }
    
    // ToString
    @Override
    public String toString() {
        return "Utilisateur{" + "nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }
   
}
