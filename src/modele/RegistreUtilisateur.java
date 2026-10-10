package modele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class RegistreUtilisateur {
    
    private ArrayList<Utilisateur> registre;
    private static final String FICHIER = System.getProperty("user.home") + "/consulaire/utilisateurs.txt";
    
    // Constructeur
    public RegistreUtilisateur() {
        registre = new ArrayList<>();
        new java.io.File(System.getProperty("user.home") + "/consulaire").mkdirs();
        chargerUtilisateurs(); // charge au démarrage
    }
    
    // Getter
    public ArrayList<Utilisateur> getRegistre() {
        return registre;
    }
    
    // Setter
    public void setRegistre(ArrayList<Utilisateur> registre) {
        this.registre = registre;
    }
    
    // Ajouter un utilisateur ET sauvegarder
    public void ajouterUtilisateur(Utilisateur u) {
        registre.add(u);
        sauvegarderUtilisateurs(); // sauvegarde automatiquement
    }
    
    // Vérifier si un email existe déjà
    public boolean emailExiste(String email) {
        for (Utilisateur u : registre) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    
    // Connecter un utilisateur
    public Utilisateur connecter(String email, String motDePasse) {
        for (Utilisateur u : registre) {
            if (u.getEmail().equals(email) && u.verifierMotDePasse(motDePasse)) {
                return u;
            }
        }
        return null;
    }
    
    // Sauvegarder dans le fichier
    public void sauvegarderUtilisateurs() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHIER))) {
            for (Utilisateur u : registre) {
                pw.println(u.getNom() + "," + u.getPrenom() + "," + 
                       u.getEmail() + "," + u.getMotDePasse() + "," +
                       u.getTelephone() + "," + u.getAdresse() + "," +
                       u.getVille() + "," + u.getCodePostal() + "," +
                       u.getPays() + "," + u.getCheminPhoto());
            }
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde : " + e.getMessage());
        }
    }
    
    // Charger depuis le fichier
    public void chargerUtilisateurs() {
        try (BufferedReader br = new BufferedReader(new FileReader(FICHIER))) {
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] data = ligne.split(",");
                if (data.length >= 4) {
                    Utilisateur u = new Utilisateur(data[0], data[1], data[2], data[3]);
                    if (data.length == 10) {
                        u.setTelephone(data[4]);
                        u.setAdresse(data[5]);
                        u.setVille(data[6]);
                        u.setCodePostal(data[7]);
                        u.setPays(data[8]);
                        u.setCheminPhoto(data[9]);
                    }
                    registre.add(u);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Aucun fichier trouvé, démarrage avec liste vide.");
        } catch (IOException e) {
            System.out.println("Erreur chargement : " + e.getMessage());
        }
    }
    
 // Mettre à jour un utilisateur existant
public void mettreAJourUtilisateur(Utilisateur uModifie) {
    for (int i = 0; i < registre.size(); i++) {
        if (registre.get(i).getEmail().equals(uModifie.getEmail())) {
            registre.set(i, uModifie);
            break;
        }
    }
    sauvegarderUtilisateurs(); // sauvegarder après modification
}
    // ToString
    @Override
    public String toString() {
        return "RegistreUtilisateur{" + "registre=" + registre + '}';
    }
}