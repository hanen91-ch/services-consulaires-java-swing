/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modele;


import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Utilisateur
 */
public class RegistreUtilisateur {
    
    private ArrayList<Utilisateur> registre;
    private static final String FICHIER = "C:\\temp\\utilisateurs.txt";
    
    
    // Constructeur
    public RegistreUtilisateur() {
        registre = new ArrayList<>();
        chargerUtilisateurs(); // charge depuis le fichier au démarrage
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
        sauvegarderUtilisateurs(); // sauvegarde automatiquement
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
    
    // méthode pour sauvegarder dans le fichier
    public void sauvegarderUtilisateurs(){
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER))){
            for (Utilisateur u : registre) {
                pw.println(u.getNom() + "," + u.getPrenom() + "," + u.getEmail() + "," + u.getMotDePasse());
            }
        }catch(IOException e){
            System.out.println("Erreur sauvegarde : " + e.getMessage());
        }
    }
    
    // méthode pour charger depuis le fichier
    public void chargerUtilisateurs() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER))){
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] data = ligne.split(",");
                if (data.length == 4) {
                    registre.add(new Utilisateur(data[0], data[1], data[2], data[3]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier trouvé, démarrage avec liste vide.");
        } catch (IOException e) {
            System.out.println("Erreur chargement : " + e.getMessage());
        }
    }
    
    
    // ToString
    @Override
    public String toString() {
        return "RegistreUtilisateur{" + "registre=" + registre + '}';
    }
    
    
}
