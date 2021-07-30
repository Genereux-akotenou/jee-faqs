<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="thematiqueBox">
    
    <%--<c:if test="${ sessionScope.toBeIncluded == 'allThematique' }">--%>
        
        <c:set var="idDiv" value="0" scope="session"/>
        <c:forEach items="${ sessionScope.allSubject }" var="unSujet">
            <c:if test="${ unSujet.getCategorie().equals(categorie) && unSujet.getReponse().length() != 0 }">
                <c:set var="idDiv" value="${ idDiv + 1 }" scope="page"/>

                <div class="questionReponseBox">
                    <div onclick="slideBox('<c:out value="${ idDiv }"/>')" class="question">
                        <c:out value="${ unSujet.getQuestion() }"/>
                    </div>
                    <div id="<c:out value="${ idDiv }"/>" class="reponse">
                        <p>
                            <c:out value="${ unSujet.getReponse() }"/>
                        </p>
                    </div>
                </div>

            </c:if>
        </c:forEach>

        <c:if test="${ idDiv == 0 }">
            <p style="font-weight: bold; font-size: 1.2em; margin-top: 6em;">Oups ! Aucune question pour cette thématique</p>
        </c:if>

        <c:set var="categorie" value="" scope="session"/>
        
    <%--</c:if>--%>
            
    <c:if test="${ sessionScope.toBeIncluded == 'userQuestions' }">
        
        <c:set var="idDiv" value="0" scope="page"/>
        <c:forEach items="${ sessionScope.allSubject }" var="unSujet">
            <c:if test="${ unSujet.getEmail().equals(sessionScope.currentUser.getEmail()) && unSujet.getReponse().length() != 0 }">
                <c:set var="idDiv" value="${ idDiv + 1 }" scope="page"/>

                <div class="questionReponseBox">
                    <div onclick="slideBox(' <c:out value="${ idDiv }"/> ')" class="question">
                        <c:out value="${ unSujet.getQuestion() }"/>
                    </div>
                    <div id=" <c:out value="${ idDiv }"/> " class="reponse">
                        <p>
                            <c:out value="${ unSujet.getReponse() }"/>
                        </p>
                    </div>
                </div>

            </c:if>
        </c:forEach>

        <c:if test="${ idDiv == 0 }">
            <p style="font-weight: bold; font-size: 1.2em; margin-top: 6em;">Vous n'avez poser aucune question !</p>
        </c:if>
        
    </c:if>
    <%--<div class="questionReponseBox">
        <div onclick="slideBox('openAnswer1')" class="question">
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Eveniet dicta aperiam ipsum illum odit sed ?
        </div>
        <div id="openAnswer1" class="reponse">
            <p>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
            </p>
        </div>
    </div>
    <div class="questionReponseBox">
        <div onclick="slideBox('openAnswer2')" class="question">
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Eveniet dicta aperiam ipsum illum odit sed ?
        </div>
        <div id="openAnswer2" class="reponse">
            <p>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
            </p>
        </div>
    </div>
    <div class="questionReponseBox">
        <div onclick="slideBox('openAnswer3')" class="question">
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Eveniet dicta aperiam ipsum illum odit sed ?
        </div>
        <div id="openAnswer3" class="reponse">
            <p>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
            </p>
        </div>
    </div>
    <div class="questionReponseBox">
        <div onclick="slideBox('openAnswer4')" class="question">
            Lorem ipsum, dolor sit amet consectetur adipisicing elit. Eveniet dicta aperiam ipsum illum odit sed ?
        </div>
        <div id="openAnswer4" class="reponse">
            <p>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
                Lorem ipsum, dolor sit amet consectetur adipisicing elit.<br>
            </p>
        </div>
    </div>--%>
</div>
<br><br><br><br><br><br><br><br>