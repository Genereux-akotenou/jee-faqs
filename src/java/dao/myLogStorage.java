/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Généreux AKOTENOU
 */
public class myLogStorage {
    public static void writeInLogFile(String log)
    {
        try {
            File myLogFile = new File("C:\\Users\\Généreux AKOTENOU\\Documents\\NetBeansProjects\\ifri-zone\\IFRI-FAQs\\web\\log.txt");
            if(!myLogFile.exists()){
                myLogFile.createNewFile();
            }else{
                FileWriter myWriter = new FileWriter(myLogFile, true);
                myWriter.write(log + "\n");
                myWriter.close();
            }
        } catch (IOException f) {
        }
    }
}
