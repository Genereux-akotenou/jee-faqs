<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="css/admin-style.css"/>

<div style="width: 85%; margin: auto;">
    
    <c:set var="emptyOrNot" value="0" scope="page"/>
    <c:set var="titleState" value="0"/>
    <c:forEach var="hashTagCategorie" items="${ sessionScope.allThematiques }">
        
        <c:forEach var="subjectToBeAnswered" items="${ sessionScope.allSubject }">
            <c:if test="${ subjectToBeAnswered.getCategorie().equals(hashTagCategorie.getLibele()) && subjectToBeAnswered.getReponse().length() == 0 }">
                
                <c:if test="${ titleState == 0 }">
                    <h4 style="text-align: center;">#<c:out value="${ hashTagCategorie.getLibele() }"/></h4>
                    <c:set var="titleState" value="${ titleState + 1 }"/>
                </c:if>
                <c:set var="emptyOrNot" value="${ emptyOrNot + 1 }" scope="page"/>
                
                <div style="margin: auto; position: relative; left: -2.5%" class='questionReponseBox'>
                    <form action="faqServlet" method="POST">
                        <div class='question'>
                            <input style="display: none;" type="text" value="<c:out value="${ subjectToBeAnswered.getId() }"/>" name="questionId"/>
                            <input style="display: none;" type="text" value="<c:out value="${ subjectToBeAnswered.getEmail() }"/>" name="questionAuthEmail"/>
                            <input style="display: none;" type="text" value="<c:out value="${ subjectToBeAnswered.getQuestion() }"/>" name="question"/><%----%> 
                            <span style="word-wrap: break-word;"><c:out value="${ subjectToBeAnswered.getQuestion() }"/><span>
                        </div>  
                        <div class='reponse' style='padding: 1em; padding-bottom: 2em;'>
                            <textarea name="reponseAdmin" cols="30" rows="10"></textarea>
                            <input type='submit' name="repondre" value='Envoyer'>
                        </div>
                    </form>
                </div>
                        
            </c:if>
        </c:forEach>
                    
        <c:set var="titleState" value="0"/>
    </c:forEach>
            
    <c:if test="${ emptyOrNot == 0 }">
        <p style="font-weight: bold; font-size: 1.2em; margin-top: 7em; margin-bottom: 7em; text-align: center;">Oups ! <br/>Vous n'avez aucunne nouvelle question</p>
    </c:if>
</div>
