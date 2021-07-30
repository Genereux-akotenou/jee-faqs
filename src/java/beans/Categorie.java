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
public class Categorie {
    private String libele;
    private String description;
    private String image;

    public Categorie(String libele, String description, String image) {
        this.libele = libele;
        this.description = description;
        this.image = image;
    }

    public String getLibele() {
        return libele;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Categorie{" + "libele=" + libele + ", description=" + description + ", image=" + image + '}';
    }
    
    
}
