/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Categorie;
import beans.sujet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;

/**
 *
 * @author Généreux AKOTENOU
 */
public class sujetRepository {
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
    
    public static int insererSujet(sujet s)
    {
        System.out.println("##-->after-->## : " + s.getEmail());
        int result = 10000;
        if(!driverState){
            driverState = loadDriver();
        }
        if(driverState){
            try{
                connexion = DriverManager.getConnection(url, user, password);
                statement = (Statement)connexion.createStatement();
                resultat = statement.executeQuery("SELECT id FROM categorie WHERE libele = '" + s.getCategorie() + "';");//s.getCategorie()
                myLogStorage.writeInLogFile("0000000000000000"+s.getCategorie());
                while(resultat.next()){
                    myLogStorage.writeInLogFile("id cat --> : " + resultat.getString("id"));
                    //myLogStorage.writeInLogFile("dddddddd == " + resultat.getString("id"));
                    String query = "INSERT INTO sujet (question, reponse, idCategorie, email) VALUES ('" + s.getQuestion() + "','" + s.getReponse() + "','" + resultat.getString("id") + "', '" + s.getEmail() + "');";
                    result = statement.executeUpdate(query);
                    myLogStorage.writeInLogFile("res rp --> : " + result);
                    break;
                }

                myLogStorage.writeInLogFile("Succes : Suject inserted succesfully\tOn " + new Date());
            }
            catch(SQLException e){
                myLogStorage.writeInLogFile("Error  : DATABASE error++\t\t\tOn " + new Date() + "\n************************ Details ************************\n"+ e + "\n*********************************************************\n");
            }
            finally{
                closeDbComponent();
            }
        }
        return result;
        //dataBaseSujets.add(s);
    }
    public static List<sujet> getAllSujet()
    {
        List<sujet> dataBaseSujets = new ArrayList<>();
        if(!driverState){
            driverState = loadDriver();
        }
        if(driverState){
            try{
                //tentative de connexion a la DB
                connexion = DriverManager.getConnection(url, user, password);
                //creation de l'objet permrttant la formulation des requetes
                statement = (Statement)connexion.createStatement();
                resultat = statement.executeQuery("SELECT sujet.id, question, reponse, libele, email FROM sujet INNER JOIN categorie ON categorie.id = sujet.idCategorie;");
                myLogStorage.writeInLogFile("Succes : get all suject succesfully\tOn " + new Date());
                while(resultat.next()){
                    dataBaseSujets.add(new sujet(resultat.getInt("id"), resultat.getString("question"), resultat.getString("reponse"), resultat.getString("libele"), resultat.getString("email")));
                }
            }
            catch(SQLException e){
                myLogStorage.writeInLogFile("Error  : DATABASE error\t\t\tOn " + new Date() + "\n************************ Details ************************\n"+ e + "\n*********************************************************\n");
            }
            finally{
                closeDbComponent();
            }
        }
        return dataBaseSujets;
    }
    public static int answerToQuestion(String idQuestion, String answer)
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
                //statement = (Statement)connexion.createStatement();
                //String query = "UPDATE sujet SET reponse = '" + answer + "' WHERE id = '" + idQuestion + "';";
                PreparedStatement ps = connexion.prepareStatement("UPDATE sujet SET reponse = ? WHERE id = ?");
                ps.setString(1, answer);
                ps.setString(2, idQuestion);
                System.err.println("UPDATE sujet SET reponse = '" + answer + "' WHERE id = '" + idQuestion + "';");
                result = ps.executeUpdate();
                //result = statement.executeUpdate(query);
                myLogStorage.writeInLogFile("Succes : ADM Response inserted succesfully\tOn " + new Date());
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
    public static int addThematique(Categorie c)
    {
        int result = 1000;
        if(!driverState){
            driverState = loadDriver();
        }
        if(driverState){
            try{
                //tentative de connexion a la DB
                connexion = DriverManager.getConnection(url, user, password);
                //creation de l'objet permrttant la formulation des requetes
                statement = (Statement)connexion.createStatement();
                String query = "INSERT INTO categorie (libele, description, image) VALUES('" + c.getLibele() + "', '" + c.getDescription() + "', '" + c.getImage() + "');";
                result = statement.executeUpdate(query);
                myLogStorage.writeInLogFile("Succes : SUJECT added succesfully\tOn " + new Date());
            }
            catch(SQLException e){
                myLogStorage.writeInLogFile("Error  : DATABASE error\t\t\tOn " + new Date() + "\n************************ Details ************************\n"+ e + "\n*********************************************************\n");
            }
            finally{
                closeDbComponent();
            }
        }
        return result;
        //allThematiques.add(t);
    }
    public static List<String> getAllThematique()
    {
        List<String> allThematiques = new ArrayList<>();
        if(!driverState){
            driverState = loadDriver();
        }
        if(driverState){
            try{
                //tentative de connexion a la DB
                connexion = DriverManager.getConnection(url, user, password);
                //creation de l'objet permrttant la formulation des requetes
                statement = (Statement)connexion.createStatement();
                String query = "SELECT libele FROM categorie;";
                resultat = statement.executeQuery(query);
                myLogStorage.writeInLogFile("Succes : DataBase suject returned succesfully\tOn " + new Date());
                while(resultat.next())
                {
                    allThematiques.add(resultat.getString("libele"));
                }
            }
            catch(SQLException e){
                myLogStorage.writeInLogFile("Error  : DATABASE error\t\t\tOn " + new Date() + "\n************************ Details ************************\n"+ e + "\n*********************************************************\n");
            }
            finally{
                closeDbComponent();
            }
        }
        return allThematiques;
    }
    
    public static List<Categorie> getThematiqueObject()
    {
        List<Categorie> allThematiques = new ArrayList<>();
        if(!driverState){
            driverState = loadDriver();
        }
        if(driverState){
            try{
                //tentative de connexion a la DB
                connexion = DriverManager.getConnection(url, user, password);
                //creation de l'objet permrttant la formulation des requetes
                statement = (Statement)connexion.createStatement();
                String query = "SELECT * FROM categorie;";
                resultat = statement.executeQuery(query);
                myLogStorage.writeInLogFile("Succes : DataBase suject returned succesfully **\tOn " + new Date());
                while(resultat.next())
                {
                    allThematiques.add( new Categorie(resultat.getString("libele"), resultat.getString("description"), resultat.getString("image")) );
                }
            }
            catch(SQLException e){
                myLogStorage.writeInLogFile("Error  : DATABASE error\t\t\tOn " + new Date() + "\n************************ Details ************************\n"+ e + "\n*********************************************************\n");
            }
            finally{
                closeDbComponent();
            }
        }
        return allThematiques;
    }
    
    public static void main(String[] args) {
        for (String theme : sujetRepository.getAllThematique()) {
            System.out.println(theme);
            
        }
    }
}
