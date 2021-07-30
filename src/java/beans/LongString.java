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
public class LongString {
    String headString;
    String bodyString;
    String footerString;

    public LongString(String headString, String bodyString, String footerString) {
        this.headString = headString;
        this.bodyString = bodyString;
        this.footerString = footerString;
    }
    
    public String getHeadString() {
        return headString;
    }

    public String getBodyString() {
        return bodyString;
    }

    public String getFooterString() {
        return footerString;
    }

    public void setHeadString(String headString) {
        this.headString = headString;
    }

    public void setBodyString(String bodyString) {
        this.bodyString = bodyString;
    }

    public void setFooterString(String footerString) {
        this.footerString = footerString;
    }
    
    
}

