/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author sanab
 */
public class Validateur {
    // vérifie qu'un champ texte n'est pas vide ou null
    public static boolean champVide(String champ) {
        return champ == null || champ.trim().isEmpty();
    }

    // vérifie que le format d'un email est valide
    public static boolean emailValide(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    // vérifie qu'un menu déroulant a une valeur sélectionnée
    public static boolean comboBoxSelectionne(String valeur) {
        return valeur != null && !valeur.equals("-- Sélectionner --");
    }

    // vérifie qu'un créneau horaire a été sélectionné
    public static boolean creneauSelectionne(String creneau) {
        return creneau != null && !creneau.isEmpty();
    }

    // vérifie qu'une date complète est sélectionnée
    public static boolean dateValide(String jour, String mois, String annee) {
        return jour != null && mois != null && annee != null;
    }
}
