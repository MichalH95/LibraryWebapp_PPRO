<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Knihy</title>
</head>
<body>
<h1>Výpis knih</h1>


    <c:forEach var="k" items="${knihy}">
        <tr>
            Název: <c:out value="${k.nazev }" />
            Popis: <c:out value="${k.popis }" />
            Žánr: <c:out value="${k.zanr }" />
            Datum vydání: <c:out value="${k.datum_vydani }" />
            Autoři:
        </tr>
<br>

    </c:forEach>

<c:forEach var="a" items="${autori}">
    <tr>
        Autori: <c:out value="${a.jmeno }" />


    </tr>
    <br>

</c:forEach>






</body>
</html>