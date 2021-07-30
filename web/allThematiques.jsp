<%@page import="dao.sujetRepository"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${ !categorie.equals(\"\") }">
        
        <jsp:include page="voirQuestions.jsp"></jsp:include>
                
    </c:when>
    <c:otherwise>
        
        <div id="thematiqueBox">
            <c:forEach items="${ sessionScope.allThematiques }" var="uneCategorie" >
                <a href="faq.jsp?categorie=<c:out value="${ uneCategorie.getLibele() }"/> ">
                    <div class="themBox">
                        <div class="img-theme">
                            <img src="<c:out value="${ uneCategorie.getImage() }"/>" alt="Erreur de chargement de l'image.  Veillez reactualiser la page.">
                        </div>
                        <span class="description-themme">
                            <c:out value="${ uneCategorie.getDescription() }"/>
                        </span>
                        <span class="titre-theme">
                            <c:out value="${ uneCategorie.getLibele() }"/>
                        </span>
                    </div>
                </a>
            </c:forEach>
            
        <p class="text-theme">Questions frequemment posées</p>

        <div id="frequentlyAskBox">
            <div style="width: 100%; margin: auto;">
                <div class="questionReponseBox" style="width: 97%;">
                    <div onclick="slideBox('openAnswer4')" class="question">
                        Comment obtenir une bourse a ifri ?
                    </div>
                    <div id="openAnswer4" class="reponse">
                        <p>
                            Etre titulaire de bac scientifique.<br>
                            Avoir au mois 14 de moyenne au bac.<br>
                            Avoir unemmotivation reelle pour l'informatique.<br>
                            Une fois ces conditions remplies passer au secretatriat de l'IFRI <br> pour plus d'informations.
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div id="frequentlyAskBox">
            <div style="width: 100%; margin: auto;">
                <div class="questionReponseBox" style="width: 97%;">
                    <div onclick="slideBox('openAnswer5')" class="question">
                        Quels sont les langages abordés a l'IFRI ?
                    </div>
                    <div id="openAnswer5" class="reponse">
                        <p>
                            HTML - CSS - JAVASCRIPT.<br>
                            C  - C++ - JAVA.<br>
                            PYTHON - C# - ASSEMBLEUR.<br>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <div id="frequentlyAskBox">
            <div style="width: 100%; margin: auto;">
                <div class="questionReponseBox" style="width: 97%;">
                    <div onclick="slideBox('openAnswer6')" class="question">
                        Pourquoi autant de mathematiques a ifri ?
                    </div>
                    <div id="openAnswer6" class="reponse">
                        <p>
                            <bold>Reponse de Docteur Genereux AKOTENOU, IA consultant and RASBERY PI IOT-DEV</bold>.<br>
                            L'essence de l'informatique c'est les maths<br>
                            On ne peut se pretendre informaticien si l'on ne peut comprendre les maths derière et pouvoir les appliqués .<br>
                        </p>
                    </div>
                </div>
            </div>
        </div>

        <br>
        
    </c:otherwise>
</c:choose>
