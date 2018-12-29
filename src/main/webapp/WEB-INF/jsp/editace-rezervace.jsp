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
                <h2>Editace rezervace</h2>
                <c:forEach var="r" items="${rezervace}">
                    <form action="/upravrezervaci">
                        <table>

                            <tr>
                                <td>Rezervace od:</td>
                                <td><input type="text" required name="rezervace_od" value="${r.rezervace_od}"></td>
                                <input type="hidden" name="idecko" value="${r.id}">
                            </tr>
                            <tr>
                                <td>Rezervace do:</td>
                                <td><input type="text" required name="rezervace_do" value="${r.rezervace_do}"></td>
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
