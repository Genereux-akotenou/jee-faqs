/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Utilisateur;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
/**
 *
 * @author Généreux AKOTENOU
 */
public class usersRepository {
    private static final String url = "jdbc:mysql://localhost:3306/ifrifaqdb";
    private static final String user = "root";
    private static final String password = "";
    
    private static Connection connexion = null;
    private static Statement statement = null;
    private static ResultSet resultat = null;
    
    private static boolean driverState = false;
    
    private static boolean loadDriver(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            myLogStorage.writeInLogFile("Succes : Driver loading succesfully\tOn " + new Date());
            return true;
        } 
        catch(ClassNotFoundException e){
            myLogStorage.writeInLogFile("Error  : An error occured while loading driver\t\tOn " + new Date() + "\n************************ Details ************************\n"+ e + "\n*********************************************************\n");
            return false;
        }
    }
    
    private static void closeDbComponent(){
        if(resultat != null){
            try{resultat.close();}catch(SQLException ignore){}
        }
        if(statement != null){
            try{statement.close();}catch(SQLException ignore){}
        }
        if(connexion != null){
            try{connexion.close();}catch(SQLException ignore){}
        }
    }
    
    public static int insererUser(Utilisateur u)
    {
        int result = 10000;
        if(!driverState){
            driverState = loadDriver();
        }
        if(driverState){
            try{
                //tentative de connexion a la DB
                connexion = DriverManager.getConnection(url, user, password);
                //creation de l'objet permrttant la formulation des requetes

                /*statement = (Statement)connexion.createStatement();
                String query = "INSERT INTO Utilisateurs (username, password, email, dateInscription, statut) VALUES(?,MD5(?),?, NOW(),?);";
                PreparedStatement stm = connexion.prepareStatement(query);
                stm.setString(1, u.getPseudo());
                stm.setString(2, u.getMotDePasse());
                stm.setString(3, u.getEmail());
                stm.setString(4, u.getStatut());
                result = stm.executeUpdate(query);*/

                statement = (Statement)connexion.createStatement();
                String query = "INSERT INTO Utilisateurs (username, password, email, dateInscription, statut) VALUES ('" + u.getPseudo() + "', MD5('" + u.getMotDePasse() + "'),'" + u.getEmail() + "', NOW(), '" + u.getStatut() + "');";
                result = statement.executeUpdate(query);
                myLogStorage.writeInLogFile("Succes : User registered succesfully\tOn " + new Date());
            }
            catch(SQLException e){
                myLogStorage.writeInLogFile("Error  : DATABASE error\t\t\tOn " + new Date() + "\n************************ Details ************************\n"+ e + "\n*********************************************************\n");
            }
            finally{
                closeDbComponent();
            }
        }
        return result;
    }
    public static Utilisateur checkIfUserExist(String login, String pwd)
    {
        Utilisateur User = null;
        if(!driverState){
            driverState = loadDriver();
        }
        if(driverState){
            try{
                connexion = DriverManager.getConnection(url, user, password);
                statement = (Statement)connexion.createStatement();
                String query = "SELECT * FROM Utilisateurs WHERE username = '" + login + "' AND password = MD5('" + pwd + "') ";
                resultat = statement.executeQuery(query);
                myLogStorage.writeInLogFile("Succes : SELECT from database | QUERY\tOn " + new Date());
                if(resultat.next()){
                    User = new Utilisateur(resultat.getString("username"),resultat.getString("email"),resultat.getString("password"),resultat.getString("statut"));
                    myLogStorage.writeInLogFile("########################################################################\n###CONNEXION ETABLISHED BY : " + User.getPseudo() + "\t\tOn " + new Date() + "\n########################################################################\n");
                }    
            }
            catch(SQLException e){
                myLogStorage.writeInLogFile("Error  : DATABASE error\t\t\tOn " + new Date() + "\n************************ Details ************************\n"+ e + "\n*********************************************************\n");
            }
            finally{
                closeDbComponent();
            }
        }
        //myLogStorage.writeInLogFile(User.toString());
        return User;
    }
}
