/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bj.ifri.servlet;

import java.time.LocalDateTime;
import java.time.LocalDate;
import beans.Categorie;
import beans.Utilisateur;
import beans.sujet;
import dao.Mail;
import dao.myLogStorage;
import dao.sujetRepository;
import dao.usersRepository;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Généreux AKOTENOU
 */

@MultipartConfig(fileSizeThreshold=1024*1024*2, 
    maxFileSize=1024*1024*10, 
    maxRequestSize=1024*1024*50
)


public class faqServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
    <multipart-config>
        <location>/tmp</location>
        <max-file-size>20848820</max-file-size>
        <max-request-size>418018841</max-request-size>
        <file-size-threshold>1048576</file-size-threshold>
    </multipart-config>
    */
    
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet faqServlet</title>");            
            out.println("</head>");
            out.println("<body style='background-color: #d3d3d5;'>");
            out.println("<div style='position: absolute; top: 0; bottom: 0; left: 0; right: 0;'> <h1 style='color: red; text-align: center; margin-top: 23%;'>IFRI - FAQs | Servlet</h1> </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //processRequest(request, response);
        
        /*======================VARIABLES  GLOBALES=============================*/
        HttpSession maSession = request.getSession();
          
        /*=================INITIALISATION DES VARIABLES=========================*/
                   
        if(request.getParameter("action") != null)
        {
            if(request.getParameter("action").equals("setData")){
                //do that in all situation
                maSession.setAttribute("allThematiques", sujetRepository.getThematiqueObject());
                maSession.setAttribute("allSubject", sujetRepository.getAllSujet());
                                
                response.sendRedirect("faq.jsp");
            }   
        }
        else if(request.getParameter("link") != null)
        {
            maSession.setAttribute("toBeIncluded", request.getParameter("link"));

            maSession.setAttribute("allThematiques", sujetRepository.getThematiqueObject());
            maSession.setAttribute("allSubject", sujetRepository.getAllSujet());
            System.err.println("xxx-->xxx : " + "include var");
            //System.err.println("Var set in session : " + request.getParameter("link"));
            response.sendRedirect("faq.jsp");
        }
        else if(request.getParameter("way") != null && request.getParameter("way").equals("logout")){
            Utilisateur u = (Utilisateur)maSession.getAttribute("currentUser");
            String toBeWrote = "########################################################################\n###CONNEXION ENDED BY : " + u.getPseudo() + "\t\tOn " + new Date() + "\n########################################################################\n";
            try {
                File myLogFile = new File("C:\\Users\\Généreux AKOTENOU\\Documents\\NetBeansProjects\\ifri-zone\\IFRI-FAQs\\web\\log.txt");
                if(!myLogFile.exists()){
                    myLogFile.createNewFile();
                }else{ 
                    FileWriter myWriter = new FileWriter(myLogFile, true);
                    myWriter.write(toBeWrote + "\n");
                    myWriter.close();
                }
            } 
            catch (IOException ignore) {
                
            }
            System.err.println("xxx-->xxx : " + "logout act");
            maSession.invalidate();
            response.sendRedirect("faq.jsp");
            //return;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*======================VARIABLES  GLOBALES=============================*/
        HttpSession maSession = request.getSession();
        String rapport, color;
        int nbrQuestionForCurrenUser = 0;
        
        /*======================INSCRIPTION CONTROL=============================*/
        if(request.getParameter("inscription") != null)
        {
            String pseudo =  request.getParameter("login");
            String email =  request.getParameter("mail");
            String pass1 =  request.getParameter("password-check1");
            String pass2 =  request.getParameter("password-check2");
            String remenber = request.getParameter("remenberMe");
            
            if(pseudo.length() != 0 && email.length() != 0 && pass1.length() != 0 && pass2.length() != 0 && pass1.equals(pass2))
            {
                System.err.println("xxx-->xxx : " + "insc act");
                Utilisateur user = new Utilisateur(pseudo, email, pass1, "SimpleUser");
                int status = usersRepository.insererUser(user);
                
                if(status == 1){
                    rapport = "Vous avez été enrégistré(e) avec succès. Connectez vous à présent. ";
                    color = "green";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                    maSession.setAttribute("toBeIncluded", "connect");
                    response.sendRedirect("faq.jsp");
                }
                else{
                    rapport = "Oups ! Cette adresse mail est déjà utilisée";
                    color = "red";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                    maSession.setAttribute("toBeIncluded", "connect");
                    response.sendRedirect("faq.jsp");
                }
                
            }
            else
            {
                if(pseudo.length() == 0 || email.length() == 0 ||  pass1.length() == 0 || pass2.length() == 0)
                {
                    rapport = "Oups ! Vous devez remplir tous les champs.";
                    color = "red";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                }
                else if(!pass1.equals(pass2))
                {
                    rapport = "Oups ! les deux mots de passe ne correspondent pas.";
                    color = "red";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                }
                maSession.setAttribute("toBeIncluded", "connect");
                response.sendRedirect("faq.jsp");
            }
        }
        /*=======================CONNEXION CONTROL==============================*/
        if(request.getParameter("connexion") != null)
        {
            System.err.println("xxx-->xxx : " + "conn act");
            String pseudo =  request.getParameter("login");
            String password =  request.getParameter("password");
            if(pseudo.length() != 0 && password.length() != 0 )
            {  
                try{
                    Utilisateur User = usersRepository.checkIfUserExist(pseudo, password);
                    myLogStorage.writeInLogFile(User.toString());
                    maSession.setAttribute("currentUser", User);
                    
                    if( User.getStatut().equals("Cobra") ){
                        for(sujet s : (List<sujet>)maSession.getAttribute("allSubject")){
                            if(s.getReponse().length() == 0){
                                nbrQuestionForCurrenUser++;
                            }
                        } 
                    }
                    else if(User.getStatut().equals("SimpleUser")){
                        for(sujet s : (List<sujet>)maSession.getAttribute("allSubject")){
                            if(s.getEmail().equals(User.getEmail()) && s.getReponse().length() != 0){
                                nbrQuestionForCurrenUser++;
                            }
                        }
                    }
                    maSession.setAttribute("nbrQuestionCurrentUser", nbrQuestionForCurrenUser);
                    response.sendRedirect("faq.jsp");
                }
                catch(Exception e)
                {
                    rapport = "identifiant ou mot de passe INCORRECT";
                    color = "red";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                    maSession.setAttribute("toBeIncluded", "connect");
                    response.sendRedirect("faq.jsp");
                }
            }
            else
            {
                rapport = "Oups ! Vous devez remplir tous les champs !";
                color = "red";
                maSession.setAttribute("state", rapport);
                maSession.setAttribute("errorColor", color);
                maSession.setAttribute("toBeIncluded", "connect");
                response.sendRedirect("faq.jsp");
            }
        }
        /*=======================POSER   QUESTIOBN==============================*/
        if(request.getParameter("askQuestion") != null)
        {
            System.err.println("xxx-->xxx : " + "ask act");
            String question = request.getParameter("userQuestion");
            String categorie = request.getParameter("categorie");
            String email = request.getParameter("email");
            System.out.println("##-->before-->## : " + email);
                
            if(question.length() != 0 && categorie.length() != 0)
            {
                int status = sujetRepository.insererSujet(new sujet(0, question, "", categorie, email));

                if(status == 1){
                    rapport = "Votre question a été bien enrégistrée. Un mail vous paviendra des qu'un spécialiste aura repondu a cette derniere";
                    color = "green";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                    response.sendRedirect("faq.jsp");
                }
                else{
                    rapport = "Oups ! Une erreur s'est produite.";
                    color = "red";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                    response.sendRedirect("faq.jsp");
                }
            }
            else if(question.length() == 0)
            {
                rapport = "Oups ! Veillez saisir la question";
                color = "red";
                maSession.setAttribute("state", rapport);
                maSession.setAttribute("errorColor", color);
                response.sendRedirect("faq.jsp");
            }
            else if(categorie.length() == 0)
            {
                rapport = "Oups ! Cette catégorie n'existe pas. Veillez choisir une catégorie valide";
                color = "red";
                maSession.setAttribute("state", rapport);
                maSession.setAttribute("errorColor", color);
                response.sendRedirect("faq.jsp");
            }
            System.out.println(maSession.getAttribute("state"));
        }
        /*======================ENREGISTRER UN THEME============================*/
        if(request.getParameter("submitTheme") != null)
        {
            String themeToBeAdded =  request.getParameter("theme").toUpperCase();    
            String themeDescription =  request.getParameter("desc").toLowerCase(); 
            List<String> themeAlreadyCreated = sujetRepository.getAllThematique();
            
            Part filePart = request.getPart("image");
            System.err.println("#01#");
            String photo="";
            String path="C:\\Users\\Généreux AKOTENOU\\Documents\\NetBeansProjects\\ifri-zone\\IFRI-FAQs\\web\\uploadedFile";/*IFRI-FAQs/web/img*/  /*C:\\Users\\Généreux AKOTENOU\\Documents\\NetBeansProjects\\ifri-zone\\IFRI-FAQs\\web\\*/
            File file=new File(path);
            System.err.println("#02#");
            file.mkdir();
            System.err.println("#03#");
            String fileName = getFileName(filePart);
            System.err.println("#04#");
            
            OutputStream out = null;
            InputStream filecontent = null;
            System.err.println("#05#");
            
            PrintWriter writer = response.getWriter();
            System.err.println("#06#");
            try {
                out = new FileOutputStream(new File(path + File.separator + fileName));
                System.err.println("#07#");
                filecontent = filePart.getInputStream();
                System.err.println("#07#");
                int read = 0;
                final byte[] bytes = new byte[1024];
                System.err.println("#08#");

                while ((read = filecontent.read(bytes)) != -1) {
                    System.err.println("#~09#");
                    out.write(bytes, 0, read);
                    photo=path+"/"+fileName;
                    System.err.println("#09~#");
                }
                System.err.println("#10#");
                
                System.err.println("######################################################");
                System.err.println("####Photo link : " + photo);
                System.err.println("######################################################");
                /*
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("URL", "USERNAME", "PASSWORD");
                Statement stmt=con.createStatement();

                stmt.executeUpdate("insert into name(Name,photourl) values('"+name+"','"+photo+"')"); 
                */
            }
            catch(Exception e)
            {
                System.err.println("-----------------------------------------------------------");
                System.err.println("An error occur : \n" + e);
                System.err.println("-----------------------------------------------------------");
            }

            if(themeToBeAdded.length() != 0  && themeDescription.length() != 0 && photo.length() != 0)
            {
                boolean status = false;
                for(String theme : themeAlreadyCreated)
                {
                    if(theme.equals(themeToBeAdded))
                    {
                        rapport = "Oups ! Cet sujet a été déjà créé.";
                        color = "red";
                        maSession.setAttribute("state", rapport);
                        maSession.setAttribute("errorColor", color);
                        response.sendRedirect("faq.jsp");
                        status = true;
                        break;
                    }
                }
                if(!status)
                {
                    System.err.println(photo);
                    int statut = sujetRepository.addThematique(new Categorie(themeToBeAdded, themeDescription, photo.replace("C:\\Users\\Généreux AKOTENOU\\Documents\\NetBeansProjects\\ifri-zone\\IFRI-FAQs\\web\\", "") ));
                    if(statut == 1){
                        rapport = "Thématique ajoutée correctement.";
                        color = "green";
                        maSession.setAttribute("state", rapport);
                        maSession.setAttribute("errorColor", color);
                        maSession.setAttribute("toBeIncluded", "allThematique");
                        response.sendRedirect("index.jsp");
                    }
                    else{
                        rapport = "Oups ! une erreur c'est produite lors de l'ajout de thème.";
                        color = "red";
                        maSession.setAttribute("state", rapport);
                        maSession.setAttribute("errorColor", color);
                        response.sendRedirect("faq.jsp");
                    }
                }
            }
            else if(themeToBeAdded.length() == 0 || themeDescription.length() == 0 || photo.length() == 0)
            {
                rapport = "Oups ! Veillez remplir tous les champs";
                color = "red";
                maSession.setAttribute("state", rapport);
                maSession.setAttribute("errorColor", color);
                response.sendRedirect("faq.jsp");
            }
        }
        /*======================REPONDRE A QUESTION============================*/
        if(request.getParameter("repondre") != null)
        {
            String question = request.getParameter("question");
            String idQuestion =  request.getParameter("questionId");    
            String reponse =  request.getParameter("reponseAdmin");   
            String emailAuth = request.getParameter("questionAuthEmail");
            
            System.err.println("*************" + idQuestion + "***********");
            System.err.println("*************" + question + "***********");
            System.err.println("*************" + reponse + "***********");
            System.err.println("*************" + emailAuth + "***********");
            
            if(reponse.length() != 0)
            {
                int status = sujetRepository.answerToQuestion(idQuestion, reponse);
                System.err.println("########## " + status + "###########");
                if(status == 1){
                    rapport = "Reponse enregsitrée avec succès.";
                    color = "green";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                    maSession.setAttribute("nbrQuestionCurrentUser", (int)maSession.getAttribute("nbrQuestionCurrentUser")-1);
                    
                    if(emailAuth.length() != 0){
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate localDate = LocalDate.now();
                        String date  = dtf.format(localDate); //2016/11/16
                        
                        String sujet = "REPONSE A  PUBLICATION | IFRI-FAQs EN DATE DE " + date;
                        String msg = "<!DOCTYPE html>\n" +
                        "<html lang='fr'>\n" +
                        "<head>\n" +
                        "    <meta charset='UTF-8'>\n" +
                        "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                        "    <meta http-equiv='X-UA-Compatible' content='ie=edge'>\n" +
                        "    <title>IFRI - FAQs response</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div style='background-color: #0077d2; width: 90%; height: 34em; margin: auto; '>\n" +
                        "        <h2 style='color: white; font-weight: bold; font-size: 2em; margin-bottom: 0.5em; padding-top: 0.5em; text-align: center;'>IFRI | FAQs</h2>\n" +
                        "        <div style=\"text-align: center;\">\n" +
                        "            <a target=\"_blank\" style=' color: black; text-decoration: none; margin: auto;' href='http://localhost:8080/IFRI-FAQs/faq.jsp'>http://localhost:8080/IFRI-FAQs/faq.jsp</a>\n" +
                        "        </div>\n" +
                        "        <div style='padding: 0.5em; color: white; font-size: 1.2em; background-color: rgb(66, 65, 65); width: 90%; height: 2.5em; margin: auto; margin-top: 1em;'>\n" +
                        "            <span>En reponse a votre question :</span><span style='float: right;'>" + date + "</span><br>\n" +
                        "            <span> " + question + " ?</span>\n" +
                        "        </div>\n" +
                        "        <div style='text-align: justify; padding: 0.5em; color: black; font-size: 1.2em; background-color: white; width: 90%; height: 10em; margin: auto;'>\n" +
                        "\n" +
                        "            <span>" + reponse + "</span>\n" +
                        "        </div>\n" +
                        "        <div style=\"width: 80%; padding: 0.5em; border-radius: 10px; background-color: rgb(233, 181, 37); color: black; font-weight: bold; text-align: center; margin: auto; margin-top: 1em;\">\n" +
                        "            Etes vous satisfait de notre reponse?\n" +
                        "        </div>\n" +
                        "        <h4 style=\"text-align: center; color: white;\">IFRI<br>nous batissons l'excellence</h4>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>";
                        Mail.sendMail(emailAuth, sujet, msg);
                        System.err.println("###### MAIL SENT TO MSG AUTH ######");
                    }
                    
                    response.sendRedirect("faqServlet?link=adminShowQuestion1");
                }
                else{
                    rapport = "Oups ! Une erreur est survenue. Votre reponse n'a pas été enregistrée.";
                    color = "green";
                    maSession.setAttribute("state", rapport);
                    maSession.setAttribute("errorColor", color);
                    response.sendRedirect("faqServlet?link=adminShowQuestion1");
                }
            }
            else
            {
                rapport = "Oups ! Veillez saisir la reponse";
                color = "red";
                maSession.setAttribute("state", rapport);
                maSession.setAttribute("errorColor", color);
                response.sendRedirect("faqServlet?link=adminShowQuestion1");
            }
        }
    }   

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "This servlet can be taken like IFRI - FAQs Brain. It has been built to do all control about data stream but also each redirection in this web application.";
    }// </editor-fold>

}
