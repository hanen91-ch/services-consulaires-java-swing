/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author Utilisateur
 */
public class DemandeVisa extends Demande implements Imprimable{
     
    // Attributs spécifiques au Visa
    private String numPasseport;
    private String typeVisa;
    private String nationalite;
    private String dateDebut;
    private String dateFin;
    private String dateDelivrance;
    private String motifVoyage;
    private boolean copiePasseport;
    private boolean photoIdentite;
    private boolean justifHebergement;
    private boolean billetAvion;
    
    // Constructeur par défaut
    public DemandeVisa() {
        super();
    }
    
    // Constructeur paramétré
    public DemandeVisa(String nom, String prenom, String adresse,
                       String telephone, String email, String dateNaissance,
                       String numPasseport, String typeVisa, String nationalite,
                       String dateDebut, String dateFin) {
        super(nom, prenom, adresse, telephone, email, dateNaissance);
        this.numPasseport = numPasseport;
        this.typeVisa = typeVisa;
        this.nationalite = nationalite;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    // Getters
    public String getNumPasseport() { return numPasseport; }
    public String getTypeVisa() { return typeVisa; }
    public String getNationalite() { return nationalite; }
    public String getDateDebut() { return dateDebut; }
    public String getDateFin() { return dateFin; }
    public String getDateDelivrance() { return dateDelivrance; }
    public String getMotifVoyage() { return motifVoyage; }
    public boolean isCopiePasseport() { return copiePasseport; }
    public boolean isPhotoIdentite() { return photoIdentite; }
    public boolean isJustifHebergement() { return justifHebergement; }
    public boolean isBilletAvion() { return billetAvion; }
    
    // Setters
    public void setNumPasseport(String numPasseport) { this.numPasseport = numPasseport; }
    public void setTypeVisa(String typeVisa) { this.typeVisa = typeVisa; }
    public void setNationalite(String nationalite) { this.nationalite = nationalite; }
    public void setDateDebut(String dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(String dateFin) { this.dateFin = dateFin; }
    public void setDateDelivrance(String dateDelivrance) { this.dateDelivrance = dateDelivrance; }
    public void setMotifVoyage(String motifVoyage) { this.motifVoyage = motifVoyage; }
    public void setCopiePasseport(boolean copiePasseport) { this.copiePasseport = copiePasseport; }
    public void setPhotoIdentite(boolean photoIdentite) { this.photoIdentite = photoIdentite; }
    public void setJustifHebergement(boolean justifHebergement) { this.justifHebergement = justifHebergement; }
    public void setBilletAvion(boolean billetAvion) { this.billetAvion = billetAvion; }
    
    // Implémentation des méthodes abstraites
    @Override
    public void soumettre() {
        this.statut = "En attente ";
    }
    
    @Override
    public String genererResume() {
        return "=== DEMANDE VISA ===\n" +
               "Nom          : " + nom + "\n" +
               "Prénom       : " + prenom + "\n" +
               "N°Passeport  : " + numPasseport + "\n" +
               "Type Visa    : " + typeVisa + "\n" +
               "Nationalité  : " + nationalite + "\n" +
               "Date Début   : " + dateDebut + "\n" +
               "Date Fin     : " + dateFin + "\n" +
               "Statut       : " + statut + "\n";
    }
    
    @Override
    public String toString() {
        return "DemandeVisa{" + super.toString() + 
               ", typeVisa=" + typeVisa + "}";
    }
    
    @Override
public void imprimer() {
    System.out.println(genererResume());
}

@Override
public String genererAccuseReception() {
    return "ACCUSÉ DE RÉCEPTION - VISA\n" + genererResume();
}
    
}
