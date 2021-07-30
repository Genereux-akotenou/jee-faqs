<%-- 
    Document   : gestionErreur
    Created on : 12 mai 2021, 09:00:03
    Author     : Généreux AKOTENOU
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${ sessionScope.state.length() != 0 }">

    <div id='errorBox' style='z-index: 1; background-color: <c:out value="${ sessionScope.errorColor }"/>; text-align: center; text-align: center; width: 70%; height: 4em; position: fixed; /*top: 3.5em; left:8%;*/ bottom: 0; left: 0; right: 0; margin: auto; color: white; font-weight: bold;'>
        <div style='background-color: white; width: 100%; height: 0.2em;' >
            <div id='progressBar' style='background-color: <c:out value="${ sessionScope.errorColor }"/>; width: 0; height: 0.2em;' ></div>
        </div>
        <p style='font-size: 1.2em;'>
            <c:out value="${ sessionScope.state }"></c:out>
            <span onClick='displayBox()' style='color: white; float: right; margin-right: 0.4em; font-size: 1.2em; cursor: pointer; margin-bottom: 0.4em;'>
                <i class='fa fa-close'></i>
            </span>
        </p>
    </div>
    <% request.getSession().setAttribute("state", ""); %>
    <c:set var="sessionScope.state" value=""/>
    
    <script>
        var i = 0;
        function timeProg()
        {
            if(i <= 100) {
                document.getElementById("progressBar").style.width=i+"%";
                if(document.getElementById("progressBar").offsetWidth >= document.getElementById("errorBox").offsetWidth - 2)
                {
                    document.getElementById("errorBox").style.display="none";
                }
                setTimeout("timeProg();", 60);
                i++;
            }
        }
        
        (function timeProg()
        {
            //alert("#######JS FUNCTION CALLED HERE#######");
            if(i <= 100) {
                document.getElementById("progressBar").style.width=i+"%";

                if(document.getElementById("progressBar").offsetWidth >= document.getElementById("errorBox").offsetWidth - 2)
                {
                    document.getElementById("errorBox").style.display="none";
                }
                setTimeout("timeProg();", 60);
                i++;
            }
        })();
    </script>
</c:if>



