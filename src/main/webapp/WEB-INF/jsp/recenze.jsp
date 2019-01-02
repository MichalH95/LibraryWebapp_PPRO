<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <link rel="stylesheet" href="css/style.css"/>
    <script type="text/javascript" src="js/javascript.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Knihy</title>

</head>
<body>

<jsp:include page="common/headermenu.jsp"/>

<div class="row">
    <div class="leftcolumn">

        <div class="card">
            <div class="container">

                <jsp:include page="common/message.jsp"/>

                <%
                if(session.getAttribute("recenze")!=null){
                %>
                <c:choose>
                    <c:when test="${idkProRecen!=null}">
                <form action="/ulozrecenzi">
                    <table>
                        <tr><td class="tabulkatd">Zveřejněné jméno:</td><td><input required type="text" name="jmeno"></td></tr>
                        <tr><td class="tabulkatd">Hodnocení:</td><td> <select name="hodnoceni">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select></td></tr>
                        <input type="hidden" value="${idkProRecen}" name="idknihy">
                        <input type="hidden" value="${email}" name="emailuzivatele">
                        <tr><td class="tabulkatd">Popis: </td><td><textarea required name="popis" maxlength="2550" rows="7" cols="30"></textarea></td></tr>
                    <tr><td colspan="2"><input type="submit" value="Uložit recenzi"></td></tr>
                    </table>

                </form>
                    </c:when>
                    <c:otherwise>
                        <%
                            session.removeAttribute("recenze");
                        %>
                        <c:redirect url="/recenze"></c:redirect>
                    </c:otherwise>
                    </c:choose>
                <% }else{%>

                <h2>Zadej název knihy</h2>
                <form action="/zobrazrecenzi">
                    <table>

                    <tr><td class="tabulkatd">Přesný název knihy:</td><td><input type="text" name="nazevknihy"></td></tr>
                        <tr><td colspan="2"><input type="submit" value="Najít recenzi"></td></tr>
                    </table>
                </form>

                <%
                    String nazev =request.getParameter("nazevknihy");
                    if (nazev != null) {
                %>

                <h1>Hodnocení:  <%= nazev %></h1>
             <% } %>

                <c:forEach var="r" items="${recenze}">
                    <b>Uživatel:</b> ${r.jmeno_autora } hodnotí knihu
                    <span style="vertical-align: sub;color: sandybrown;font-size: 28px;">
                     <c:forEach begin="1" end="${r.hodnoceni}">
                         *
                     </c:forEach></span>
                    <br>
                    <b>Hodnocení:</b> ${r.recenze}
                    <br><hr>
                </c:forEach>
            <% }%>

            </div>
        </div>

    </div>
    <jsp:include page="common/rightcolumn.jsp"/>
</div>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
