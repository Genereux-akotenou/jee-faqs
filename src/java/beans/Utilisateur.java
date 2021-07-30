/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Généreux AKOTENOU
 */


public class Utilisateur {
    private String pseudo;
    private String email;
    private String motDePasse;
    private String statut;

    public Utilisateur(String pseudo, String email, String motDePasse, String statut) {
        this.pseudo = pseudo;
        this.email = email;
        this.motDePasse = motDePasse;
        this.statut = statut;
    }

    public Utilisateur() {
        this.pseudo = null;
        this.email = null;
        this.motDePasse = null;
        this.statut = null;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "\tpseudo=" + pseudo + ", \n\temail=" + email + ", \n\tmotDePasse=" + motDePasse + ", \n\tstatut=" + statut + "\n}\n";
    }
       
    public String getPseudo() {
        return pseudo;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getStatut() {
        return statut;
    }
    
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse1) {
        this.motDePasse = motDePasse1;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
