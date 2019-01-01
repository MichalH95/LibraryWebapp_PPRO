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

                <h2>Editace upomínky</h2>
                <c:forEach var="u" items="${upominky}">
                    <form action="/upravupominku">
                        <table>

                            <tr>
                                <td>Pokuta:</td>
                                <td><input type="text" onkeypress="return onlyNumbers();" required name="pokuta" value="${u.pokuta}"></td>
                                <input type="hidden" name="idecko" value="${u.id}">
                            </tr>

                            <tr>
                                <td>Popis:</td>
                            <td><textarea required name="popis" maxlength="2550" rows="7" cols="30">${u.popis}</textarea></td>

                            </tr>

                            <tr>
                                <td><input type="submit" value="Editovat"></td>
                            </tr>

                        </table>
                    </form>
                </c:forEach>

            </div>
        </div>

    </div>
    <jsp:include page="common/rightcolumn.jsp"/>
</div>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
