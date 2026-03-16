/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;

/**
 *
 * @author Utilisateur
 */
public class DemandePasseport extends Demande implements Imprimable{
    
    // Attributs spécifiques au Passeport
    private String typeDocument;
    private String motif;
    private String ancienNumDoc;
    private String ville;
    private String nomPere;
    private String nomMere;
    private String nomEpoux;
    private String prenomEpoux;
    private String dateMariage;
    private String lieuMariage;
    private int nbCopiesNaissance;
    private int nbCopiesMariage;
    
    // Constructeur par défaut
    public DemandePasseport() {
        super();
    }
    
    // Constructeur paramétré
    public DemandePasseport(String nom, String prenom, String adresse,
                            String telephone, String email, String dateNaissance,
                            String typeDocument, String motif, String ville) {
        super(nom, prenom, adresse, telephone, email, dateNaissance);
        this.typeDocument = typeDocument;
        this.motif = motif;
        this.ville = ville;
    }
    
    // Getters
    public String getTypeDocument() { return typeDocument; }
    public String getMotif() { return motif; }
    public String getAncienNumDoc() { return ancienNumDoc; }
    public String getVille() { return ville; }
    public String getNomPere() { return nomPere; }
    public String getNomMere() { return nomMere; }
    public String getNomEpoux() { return nomEpoux; }
    public String getPrenomEpoux() { return prenomEpoux; }
    public String getDateMariage() { return dateMariage; }
    public String getLieuMariage() { return lieuMariage; }
    public int getNbCopiesNaissance() { return nbCopiesNaissance; }
    public int getNbCopiesMariage() { return nbCopiesMariage; }
    
    // Setters
    public void setTypeDocument(String typeDocument) { this.typeDocument = typeDocument; }
    public void setMotif(String motif) { this.motif = motif; }
    public void setAncienNumDoc(String ancienNumDoc) { this.ancienNumDoc = ancienNumDoc; }
    public void setVille(String ville) { this.ville = ville; }
    public void setNomPere(String nomPere) { this.nomPere = nomPere; }
    public void setNomMere(String nomMere) { this.nomMere = nomMere; }
    public void setNomEpoux(String nomEpoux) { this.nomEpoux = nomEpoux; }
    public void setPrenomEpoux(String prenomEpoux) { this.prenomEpoux = prenomEpoux; }
    public void setDateMariage(String dateMariage) { this.dateMariage = dateMariage; }
    public void setLieuMariage(String lieuMariage) { this.lieuMariage = lieuMariage; }
    public void setNbCopiesNaissance(int nbCopiesNaissance) { this.nbCopiesNaissance = nbCopiesNaissance; }
    public void setNbCopiesMariage(int nbCopiesMariage) { this.nbCopiesMariage = nbCopiesMariage; }
    
    // Implémentation des méthodes abstraites
    @Override
    public void soumettre() {
        this.statut = "En attente ";
    }
    
    @Override
    public String genererResume() {
        return "=== DEMANDE PASSEPORT/CNI ===\n" +
               "Type Document : " + typeDocument + "\n" +
               "Nom           : " + nom + "\n" +
               "Prénom        : " + prenom + "\n" +
               "Ville         : " + ville + "\n" +
               "Motif         : " + motif + "\n" +
               "Nom Père      : " + nomPere + "\n" +
               "Nom Mère      : " + nomMere + "\n" +
               "Statut        : " + statut + "\n";
    }
    
    @Override
    public String toString() {
        return "DemandePasseport{" + super.toString() + 
               ", typeDocument=" + typeDocument + "}";
    }
    
    @Override
    public void imprimer() {
        System.out.println(genererResume());
    }

    @Override
    public String genererAccuseReception() {
        return "ACCUSÉ DE RÉCEPTION - PASSEPORT/CNI\n" + genererResume();
    }
}
