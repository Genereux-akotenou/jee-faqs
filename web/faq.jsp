<%-- 
    Document   : index
    Created on : 2 mai 2021, 13:30:16
    Author     : Généreux AKOTENOU
--%>

<%@page import="dao.sujetRepository"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
        <link rel="stylesheet" type="text/css" href="css/voir-question-style.css"/>
        <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
        <link rel="stylesheet" type="text/css" href="css/connect-style.css"/>
        <title>IFRI | FAQ</title>      
    </head>
    <body onclick="hideResultBox(event)" onload=" positionnerBox(); /*timeProg();*/">  <!--fadeLoad();*/ /*displayAnim();-->
        <!-- HEADER -->

        <c:if test="${ sessionScope.allThematiques == null }">
            <jsp:forward page="faqServlet?action=setData" />
        </c:if>  
              
        <jsp:include page="header.jsp"></jsp:include>
        <main>
            
            <!-- Pannel de navigation -->
            <p class="text-theme">
                <a href="faqServlet?link=allThematique">
                    <span class="activeOption bleuBox">
                        les thematiques
                    </span>
                </a>                           
                
                <c:if test="${ sessionScope.currentUser == null || sessionScope.currentUser.statut != 'Cobra'  }">
                    <span onclick='displayQuestionBox("popUpContainer")' class="activeOption whiteBox">
                        poser une question
                    </span>
                </c:if>
                
                <c:if test="${ sessionScope.currentUser.getStatut() == \"SimpleUser\" }">
                    <a href="faqServlet?link=userQuestions">
                        <span class="activeOption whiteBox">
                            mes questions
                            <span class="nbrQuestion"><c:out value="${sessionScope.nbrQuestionCurrentUser}"/></span>
                        </span>
                    </a>
                </c:if>

                <c:if test="${ sessionScope.currentUser.getStatut() == 'Cobra' }">
                    <span onclick='displayQuestionBox("popUpContainer2")' class="activeOption bleuBox">
                        ajouter thematique
                    </span>
                    <!--<i class="fa fa-exchange"></i>-->
                    <a href="faqServlet?link=adminShowQuestion1">
                        <span class="activeOption bleuBox">
                            nouvelles questions
                            <span class="nbrQuestion"><c:out value="${sessionScope.nbrQuestionCurrentUser}"/></span>
                        </span>
                    </a>
                </c:if>
            </p>
            <!-- container to be included depending on top value -->
            <c:choose>
                <c:when test="${ sessionScope.toBeIncluded == 'allThematique' }">
                    <jsp:include page="allThematiques.jsp"></jsp:include>
                </c:when>
                <c:when test="${ sessionScope.toBeIncluded == 'userQuestions' }">
                    <jsp:include page="voirQuestions.jsp"></jsp:include>
                </c:when>
                <c:when test="${ sessionScope.toBeIncluded == 'adminShowQuestion1' }">
                    <jsp:include page="questionsEnAttente.jsp"></jsp:include>
                </c:when>
                <c:when test="${ sessionScope.toBeIncluded == 'connect' }">
                    <jsp:include page="connect.jsp"></jsp:include>
                    <% request.getSession().setAttribute("toBeIncluded", ""); %>
                </c:when>
                <c:otherwise>
                    <jsp:include page="allThematiques.jsp"></jsp:include>
                </c:otherwise>
            </c:choose>
                     
        </main>
        <jsp:include page="footer.jsp"></jsp:include>
        <script src="script/myScript.js"></script>
        
        <!--Le script ici vise a mettre dans un var js les sujets enregsitres dans la session java et les proformate pour le moteur de recherhce-->
        <script>
            var clearData1 = new Array("");
            var allSubjectForJS = "[sujet{question=comment obtenir une bourse a ifri, reponse=Il faut Ãªtre titulaire de bac de sÃ©rie scientifique et avoir une bonne mention au bac. Ensuite tu depose ton dossier au secrÃ©tariat., categorie=BOURSE A IFRI, email=genereux.akotenou@gmail.com}, sujet{question=une autre  questions, reponse=voici une reponse, categorie=BOURSE A IFRI, email=genereux.akotenou@gmail.com}, sujet{question=une autre question encore et encore, reponse=#test12 des rÃ©ponses encore et encore #test12, categorie=BOURSE A IFRI, email=genereux.akotenou@gmail.com}, sujet{question=coucou jai une nouvelle question. veux tu repondre, reponse=, categorie=CODING CLUB, email=bob@mail.com}, sujet{question=definition de ia, reponse=, categorie=IA, email=bob@mail.com}, sujet{question=En quoi un programme de machine learning peut rÃ©soudre les problÃ¨mes de rÃ©gression linÃ©aires des modÃ¨les mathÃ©matiques , reponse=, categorie=IA, email=genereux.akotenou@gmail.com}, sujet{question=quelle est le contenu du programme en ia a ifri, reponse=Ce programme est trÃ¨s innovant il contient&lt;br&gt; en module sur le machine learning&lt;br&gt;un module de programmation procedurale avec python&lt;br&gt;utilisation du module numpy&lt;br&gt;Et enfin un module avec tensorflow., categorie=IA, email=genereux.akotenou@gmail.com}, sujet{question=Ou se trouve ifri, reponse=, categorie=SCOLARITE, email=anonymous}]`"; 
            <%--//allSubjectForJS = "`<c:out value="${ sujetRepository.getAllSujet() }"></c:out>`";--%>
            //allSubjectForJS.Repeat(Environment.NewLine, "<br />")
            //alert(allSubjectForJS);
            var allSplited = allSubjectForJS.split("=");
            var i = 0;
            while(allSplited.length != 0){
                if(i == 1 || i == 3){
                    clearData1.push(allSplited[0]);
                    //alert(allSplited[0]);
                }
                allSplited.shift();
                if(i == 4){
                    i = 0;
                }
                i++;
            }
            //alert("###########################\n" + clearData1 + "\n###########################");
            clearData1.shift();
            for(var i = 0; i < clearData1.length; i++){
                clearData1[i] = clearData1[i].replace(", reponse", "");
                clearData1[i] = clearData1[i].replace(", email", "");
            }
            //alert("###########################\n" + clearData1 + "\n###########################");
        </script>
    </body>
</html>
