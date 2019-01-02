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

                <h2>Vložení upomínky</h2>
                <form:form action="/vytvorupominku" autocomplete="on">
                    <table>
                       <input type="hidden" name="idknihy" value="${idknihy}">
                        <input type="hidden" name="iduzivatele" value="${iduzivatele}">
                        <input type="hidden" name="idvypujcky" value="${idvypujcky}">
                        <tr><td class="tabulkatd">Pokuta:</td><td><input required type="text" onkeypress="return onlyNumbers();" name="pokuta"> Kč</td></tr>
                        <tr><td class="tabulkatd">Popis: </td><td><textarea required name="popis" maxlength="2550" rows="7" cols="30"></textarea></td></tr>
                        <tr><td colspan="2"><input type="submit" value="Vložit upomínku"></td></tr>
                    </table>
                </form:form>

            </div>
        </div>

    </div>
    <jsp:include page="common/rightcolumn.jsp"/>
</div>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
