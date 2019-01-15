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

<style>
    input[type=checkbox] {
        transform: scale(2);
    }
</style>

<jsp:include page="common/headermenu.jsp"/>

<div class="row">
    <div class="leftcolumn">

        <div class="card">
            <div class="container">

                <jsp:include page="common/message.jsp"/>

                <h2>Editace výpůjčky</h2>
                <c:forEach var="v" items="${vypujcka}">
                    <form action="/upravvypujcku">
                        <table>

                            <tr>
                                <td>Datum vypůjčení:</td>
                                <td><input type="text" required name="datum_vypujceni" value="${v.datum_vypujceni}"></td>
                                <input type="hidden" name="idecko" value="${v.id}">
                            </tr>
                            <tr>
                                <td>vypůjčeno do:</td>
                                <td><input type="text" required name="vypujceno_do" value="${v.vypujceno_do}"></td>
                            </tr>
                            <tr>
                                <td>Vráceno:</td>
                                <td>
                                    <c:choose>
                                        <c:when test = "${v.vraceno == true}">
                                            <input style="height: 20px;" type="checkbox" name="vraceno" checked>
                                        </c:when>
                                        <c:when test = "${v.vraceno == false}">
                                            <input style="height: 20px;" type="checkbox"  name="vraceno">
                                        </c:when>
                                    </c:choose>
                                </td>
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
