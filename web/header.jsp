<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.sujetRepository" %>
<%@page import="java.util.List" %>
    
<c:set var="categorie" value="" scope="session"/>
<c:forEach var="pageParameter" items="${param}">
    <c:set var="categorie" value="${ pageParameter.value }" scope="session"/>
</c:forEach>

<header>
    <div id="right-nav">
        <a href="index.jsp">
            <div id="logoBox">
                <img src="img/logoifri.webp" alt="">
            </div>
        </a>
        <a href="index.jsp">
            <div class="top-move shadowForMobil" id="assistance">
                Centre d'assistance
            </div>
        </a>
        <a href="https://ifri-uac.bj" target="_blank">
            <div class="top-move textBold meltColor">
                Acceuil
            </div>
        </a>
        <a href="">
            <div class="top-move textBold meltColor">
                A propos
            </div>
        </a>
        <a href="#copyright">
            <div class="top-move roundBox roundBoxHover meltColor joinUs shadowForMobil">
                Contactez-nous
            </div>
        </a>
        
        <c:if test="${ sessionScope.currentUser == null}">
            <a href="faqServlet?link=connect">
                <div class="top-move roundBox roundBoxHover meltColor joinUs shadowForDesktop shadowForDesktop1">
                    Se connecter
                </div>
            </a>
        </c:if>
        <c:if test="${not empty sessionScope.currentUser}">
            <a href="faqServlet?link=logout">
                <div class="top-move roundBox roundBoxHover meltColor joinUs shadowForDesktop shadowForDesktop1">
                    Deconnexion
                </div>
            </a>
        </c:if>
    </div>
    <div id="left-nav">
        <div class="top-move">
            <a href="#researchBox"><i id="search" onclick="showAskBox1()" class="shadowForMobil fa fa-search showHand"></i></a>
            <a class="showForMobil" style="display: none;" href="#researchBox"><i id="search" onclick="showAskBox1()" class="fa fa-bars showHand bars"></i></a>
            <a href="#"><i id="close" onclick="showAskBox2()" class="fa fa-close showHand"></i></a>
        </div>
        
        <c:if test="${ sessionScope.currentUser == null}">
            <a href="faqServlet?link=connect">
                <div class="top-move roundBoxHover connectLink shadowForMobil">
                    Se connecter
                </div>
            </a>
        </c:if>
        <c:if test="${not empty sessionScope.currentUser}">
            <a href="faqServlet?way=logout">
                <div class="top-move roundBoxHover connectLink shadowForMobil">
                    Deconnexion
                </div>
            </a>
        </c:if>
        
    </div>
</header>

<div id="popUpContainer" style="position: fixed !important;">
    <div id="popUpQuestion">
        <form action="faqServlet" method="POST">
            <div id="sendHead">
                <span>POSEZ UNE QUESTION</span>
                <i onclick='displayQuestionBox("popUpContainer")' class="fa fa-close showHand"></i>
            </div>
            <div id="sendBody">
                <div>
                    <textarea name="userQuestion" id="" cols="" rows="" placeholder="Exemple : Comment obtenir une bourse d etude à IFRI."></textarea>
                </div>
                <div >
                    <label for="#categorie">Choississez une catégorie de thematique : </label><br>
                    <select name="categorie" id="themes">
                        
                        <option value="<c:out value="${categorie}"></c:out>"> <c:out value="${categorie}"></c:out> </option>
                    
                        <c:forEach var="theme" items="${ sessionScope.allThematiques }">
                            <c:if test="${ !theme.getLibele().equals(categorie) }">
                                <option value="<c:out value="${theme.getLibele()}"></c:out>"> <c:out value="${theme.getLibele()}"></c:out> </option>
                            </c:if>
                        </c:forEach>
                                
                    </select><br><br>
                                        
                    <label class="anonym1" for="anonym">Anonyme ?</label>
                    <input class="anonym2" id="anonym" type="checkbox" name=""><br><br>

                    <label id="textForEmail" for="#emailTag">Renseignez votre mail pour y reçevoir une copie des reponses a vos questions</label><br><br>
                    
                    <c:if test="${!empty sessionScope.currentUser}">
                        <input id="emailTag" type="text" name="email" placeholder="Entrez votre email" value="<c:out value="${sessionScope.currentUser.email}"></c:out>">
                    </c:if>
                    <c:if test="${empty sessionScope.currentUser}">
                        <input id="emailTag" type="text" name="email" placeholder="Entrez votre email">
                    </c:if>
                        
                    <br>
                </div>
            </div>
            <div id="sendFoot">
                <div onclick='displayQuestionBox("popUpContainer")'  class="quitAsk">Quitter</div>
                <input type="submit" name="askQuestion" value="Publier">
            </div>
        </form>
    </div>
</div>

<div id="popUpContainer2" style="position: fixed !important;">
    <div id="popUpAddThematique">
        <form action="faqServlet" method="POST" enctype="multipart/form-data">
            <div id="sendHead2">
                <span>AJOUTER UNE THEMATIQUE</span>
                <i onclick='displayQuestionBox("popUpContainer2")' class="fa fa-close"></i>
            </div>
            <div id="sendBody2">
                <div>
                    <textarea name="desc" id="" cols="" rows="" placeholder="Exemple : Donner une brève description de la thematique"></textarea>
                </div>
                <div >
                    <br>
                    <label for="categorie">Thematique : </label><br>
                    <input id="categorie" type="text" name="theme" placeholder="Ex : SCOLARITE IFRI">
                    <br><br>

                    <label for="#emailTag">Inserer une image pour la categorie</label><br><br>
                    <input id="emailTag" type="file" name="image" value="choisir le logo">
                    <br><br>
                </div>
            </div>
            <div id="sendFoot2">
                <!--<input onclick='displayQuestionBox("popUpContainer2")' type="" name="askQuestion" value="quiter">-->
                <div onclick='displayQuestionBox("popUpContainer2")' class="quitAsk">quitter</div>
                <input type="submit" name="submitTheme" value="publier">
            </div>
        </form>
    </div>
</div>

<jsp:include page="gestionErreur.jsp"></jsp:include>                        

<!--
<div id="preload" style="transition: all .3s ease-in; /*opacity: 1;*/ display: block;">
    <img style="position: absolute; margin: auto; top: 0; left: 0; bottom: 0; right: 0; " src="img/preloader.gif" alt="">
</div>
-->

<div id="subBanner">
    <%--<c:set var="date" value="${ new Date() }"></c:set>--%>
    <c:choose>
        <c:when test="${ sessionScope.currentUser == null}">
            <p class="fadeDisplay">IFRI | FAQs</p>
        </c:when>
            
        <c:when test="${(not empty sessionScope.currentUser) && sessionScope.currentUser.statut == \"SimpleUser\"}">
            <p class="fadeDisplay">
                IFRI | FAQs
            </p>
            <p class="userGreat" style="color: white; font-size: 1.4em; text-align: center; letter-spacing: 3px; position: relative; top: -2px;">
                <span class="fadeDisplay">Bienvenu à vous <c:out value="${ sessionScope.currentUser.pseudo }"></c:out></span>
            </p>
        </c:when>
            
        <c:when test="${(not empty sessionScope.currentUser) && sessionScope.currentUser.statut == \"Cobra\"}">
            <p class="fadeDisplay">
                IFRI | FAQs - Admin Workspace
            </p>
            <p class="userGreat" style="color: white; font-size: 1.4em; text-align: center; letter-spacing: 3px; position: relative; top: -2px;">
                <span class="fadeDisplay">Bienvenu à vous <c:out value="${ sessionScope.currentUser.pseudo }"></c:out></span>
            </p>
        </c:when>
    </c:choose>

    <!-- <div class="firstText"><p>En quoi pouvons nous vous aider ?</p></div>-->
    <div class="logoInBack"></div>
    <div class="logoInBack logoInBack-left"></div>
</div>

<div id="researchBox" style="background: none;">
    <div class="firstText-1">
        <p>Comment pouvons-nous vous aider ?</p>
        <input id="searchField" type="text" name="researchFiel" placeholder="Que cherchez vous ?"/>
        <i class="fa fa-search showHand searchBtn"></i>
        <div id="displayAnsBox">
            <!--<a href="#"><span><span class="black">Lire : </span><span class="themeSearch">bourse a ifri</span> <span class="dataAboutSearch">dans la <span class="themeSearch">thématique</span> : BOURSE, 10/12/20</span> </span></a><br>
            <span>Lire : <a href="#">bourse en genie logiciel</a></span><br>
            <span>Lire : <a href="#">bourse en securite informatique</a></span><br>
            <span>Lire : <a href="#">bourse en genie logiciel</a></span><br>
            <span>Lire : <a href="#">bourse en securite informatique</a></span><br>-->
        </div>
        <br>
        <a id="qfp" style="display: block;" href="#frequentlyAskBox">Questions frequemment posées</a>
    </div>
    <!-- <div class="logoInBack"></div>
    <div class="logoInBack logoInBack-left"></div> -->
</div>
<br><br><br><br>
<hr id="line" style="display: none;"/>