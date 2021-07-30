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
public class sujet {
    private int id;
    private String question;
    private String reponse;
    private String categorie;
    private String email;

    public sujet(int id, String question, String reponse, String categorie, String email) {
        this.id = id;
        this.question = question;
        this.reponse = reponse;
        this.categorie = categorie;
        if(email == null)
            this.email = "anonymous";
        else
            this.email = email;
    }

    
    public String getQuestion() {
        return question;
    }

    public String getReponse() {
        return reponse;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
    

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "sujet{" + "question=" + question + ", reponse=" + reponse + ", categorie=" + categorie + ", email=" + email + '}';
    }
    
    
    
    
    
}
