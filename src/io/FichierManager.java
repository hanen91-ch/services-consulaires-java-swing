/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import modele.DemandeVisa;
import modele.DemandePasseport;
import modele.RendezVous;

/**
 *
 * @author Utilisateur
 */
public class FichierManager {
    
    // Chemins des fichiers
    private static final String FICHIER_VISA      = System.getProperty("user.home") + "/consulaire/demandes_visa.txt";
    private static final String FICHIER_PASSEPORT = System.getProperty("user.home") + "/consulaire/demandes_passeport.txt";
    private static final String FICHIER_RDV       = System.getProperty("user.home") + "/consulaire/demandes_rdv.txt";
    
    // Créer le dossier consulaire si inexistant
    private static void creerDossier() {
        java.io.File dossier = new java.io.File(System.getProperty("user.home") + "/consulaire");
        if (!dossier.exists()) {
            dossier.mkdirs();
        }
    }
    
    // Sauvegarder une demande Visa
    public static void sauvegarderVisa(DemandeVisa v) {
        creerDossier();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER_VISA, true))) {
            bw.write("========================================"); bw.newLine();
            bw.write("Type de Visa      : " + v.getTypeVisa()); bw.newLine();
            bw.write("--- Informations Personnelles ---"); bw.newLine();
            bw.write("Nom               : " + v.getNom()); bw.newLine();
            bw.write("Prénom            : " + v.getPrenom()); bw.newLine();
            bw.write("N° Passeport      : " + v.getNumPasseport()); bw.newLine();
            bw.write("Date de Naissance : " + v.getDateNaissance()); bw.newLine();
            bw.write("Adresse           : " + v.getAdresse()); bw.newLine();
            bw.write("Nationalité       : " + v.getNationalite()); bw.newLine();
            bw.write("--- Informations Visa ---"); bw.newLine();
            bw.write("Date Début        : " + v.getDateDebut()); bw.newLine();
            bw.write("Date Fin          : " + v.getDateFin()); bw.newLine();
            bw.write("Motif de Voyage   : " + v.getMotifVoyage()); bw.newLine();
            bw.write("--- Documents Fournis ---"); bw.newLine();
            bw.write("Copie Passeport   : " + (v.isCopiePasseport() ? "Oui" : "Non")); bw.newLine();
            bw.write("Photo Identité    : " + (v.isPhotoIdentite() ? "Oui" : "Non")); bw.newLine();
            bw.write("Hébergement       : " + (v.isJustifHebergement() ? "Oui" : "Non")); bw.newLine();
            bw.write("Billet Avion      : " + (v.isBilletAvion() ? "Oui" : "Non")); bw.newLine();
            bw.write("Statut            : " + v.getStatut()); bw.newLine();
            bw.write("========================================"); bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde Visa : " + e.getMessage());
        }
    }
    
    // Sauvegarder une demande Passeport
    public static void sauvegarderPasseport(DemandePasseport p) {
        creerDossier();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER_PASSEPORT, true))) {
            bw.write("========================================"); bw.newLine();
            bw.write("Type de Document  : " + p.getTypeDocument()); bw.newLine();
            bw.write("--- Informations Personnelles ---"); bw.newLine();
            bw.write("Nom               : " + p.getNom()); bw.newLine();
            bw.write("Prénom            : " + p.getPrenom()); bw.newLine();
            bw.write("Date de Naissance : " + p.getDateNaissance()); bw.newLine();
            bw.write("Adresse           : " + p.getAdresse()); bw.newLine();
            bw.write("Ville             : " + p.getVille()); bw.newLine();
            bw.write("Téléphone         : " + p.getTelephone()); bw.newLine();
            bw.write("Email             : " + p.getEmail()); bw.newLine();
            bw.write("--- Informations Document ---"); bw.newLine();
            bw.write("Motif             : " + p.getMotif()); bw.newLine();
            bw.write("Ancien N° Doc     : " + p.getAncienNumDoc()); bw.newLine();
            bw.write("--- Acte de Naissance ---"); bw.newLine();
            bw.write("Nom&Prénom Père   : " + p.getNomPere()); bw.newLine();
            bw.write("Nom&Prénom Mère   : " + p.getNomMere()); bw.newLine();
            bw.write("--- Acte de Mariage ---"); bw.newLine();
            bw.write("Nom&Prénom Époux  : " + p.getNomEpoux()); bw.newLine();
            bw.write("Nom&Prénom Épouse : " + p.getPrenomEpoux()); bw.newLine();
            bw.write("Date Mariage      : " + p.getDateMariage()); bw.newLine();
            bw.write("Lieu Mariage      : " + p.getLieuMariage()); bw.newLine();
            bw.write("Statut            : " + p.getStatut()); bw.newLine();
            bw.write("========================================"); bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde Passeport : " + e.getMessage());
        }
    }
    
    // Sauvegarder un Rendez-vous
    public static void sauvegarderRDV(RendezVous r) {
        creerDossier();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER_RDV, true))) {
            bw.write("========================================"); bw.newLine();
            bw.write("Type de Service   : " + r.getTypeService()); bw.newLine();
            bw.write("--- Informations Personnelles ---"); bw.newLine();
            bw.write("Nom               : " + r.getNom()); bw.newLine();
            bw.write("Prénom            : " + r.getPrenom()); bw.newLine();
            bw.write("Téléphone         : " + r.getTelephone()); bw.newLine();
            bw.write("Email             : " + r.getEmail()); bw.newLine();
            bw.write("--- Détails Rendez-vous ---"); bw.newLine();
            bw.write("Date              : " + r.getDate()); bw.newLine();
            bw.write("Créneau           : " + r.getCreneau()); bw.newLine();
            bw.write("Statut            : " + r.getStatut()); bw.newLine();
            bw.write("========================================"); bw.newLine();
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde RDV : " + e.getMessage());
        }
    }
    
    // Lire tous les fichiers pour le tableau de bord
    public static javax.swing.table.DefaultTableModel lireTousFichiers() {
        String[] colonnes = {"Type", "Nom", "Prénom", "Détails", "Statut"};
        javax.swing.table.DefaultTableModel model =
            new javax.swing.table.DefaultTableModel(colonnes, 0);
        
        lireFichier(FICHIER_VISA,      "Visa",          model);
        lireFichier(FICHIER_PASSEPORT, "Passeport/CNI", model);
        lireFichier(FICHIER_RDV,       "Rendez-vous",   model);
        
        return model;
    }
    
    private static void lireFichier(String chemin, String type,
            javax.swing.table.DefaultTableModel model) {
        try {
            java.io.File fichier = new java.io.File(chemin);
            if (!fichier.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(fichier));
            String nom = "", prenom = "", details = "", statut = "";
            String ligne;

            while ((ligne = br.readLine()) != null) {

                if (ligne.contains("Nom               : "))
                    nom = ligne.replace("Nom               : ", "");

                else if (ligne.contains("Prénom            : "))
                    prenom = ligne.replace("Prénom            : ", "");

                else if (ligne.contains("Type de Service   : ") ||
                         ligne.contains("Type de Visa      : ") ||
                         ligne.contains("Type de Document  : "))
                    details = ligne.substring(ligne.indexOf(":") + 2);

                else if (ligne.contains("Statut            : "))
                    statut = ligne.replace("Statut            : ", "");

                else if (ligne.equals("========================================")
                         && !nom.isEmpty()) {
                    model.addRow(new Object[]{type, nom, prenom, details, statut});
                    nom = ""; prenom = ""; details = ""; statut = "";
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Erreur lecture " + chemin + " : " + e.getMessage());
        }
    }
    
}
