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

                <h2>Editace rezervace</h2>
                <c:forEach var="k" items="${kniha}">
                    <form action="/upravknihu">
                        <table>
                            <table>
                                <tr><td class="tabulkatd">Název knihy: </td><td><input required value="${k.nazev}" type="text" name="nazev"></td></tr>
                                <tr><td class="tabulkatd">Jazyk:</td><td> <select style="width: 150px"  name="jazyk">
                                    <option value="${k.jazyk}" selected>${k.jazyk}</option>
                                    <option value="Čeština">Čeština</option>
                                    <option value="Angličtina">Angličtina</option>
                                    <option value="Němčina">Němčina</option>
                                </select></td></tr>
                                <input type="hidden" name="idecko" value="${k.id}">
                                <tr><td class="tabulkatd">Žánr: </td><td><select style="width: 150px" name="zanr">
                                    <option value="${k.zanr}" selected>${k.zanr}</option>
                                    <option value="Sci-fi">Sci-fi</option>
                                    <option value="Horor">Horor</option>
                                    <option value="Fantasy">Fantasy</option>
                                    <option value="Detektivky">Detektivky</option>
                                    <option value="Drama">Drama</option>
                                    <option value="Poezie">Poezie</option>
                                    <option value="Odborné">Odborné</option>
                                    <option value="Pohádka">Pohádka</option>
                                </select></td></tr>

                                <tr><td class="tabulkatd">Nakladatelství:</td><td> <select style="width: 150px"  name="nakladatelstvi">
                                    <option value="${k.nakladatelstvi}" selected>${k.nakladatelstvi}</option>
                                    <option value="Albatros">Albatros</option>
                                    <option value="Prometheus">Prometheus</option>
                                </select></td></tr>
                                <tr><td class="tabulkatd">Datum vydání:</td><td><input  value="${k.datum_vydani}" required type="date" name="datum_vydani" value="2018-07-22" min="1900-01-01" required max="2019-12-31"></td></tr>
                                <tr><td class="tabulkatd">ISBN: </td><td><input required value="${k.isbn}" type="text" name="isbn"></td></tr>
                                <tr><td class="tabulkatd">Počet kusů: </td><td><input required value="${k.pocet_kusu}" type="text" onkeypress="return onlyNumbers();" name="pocet_kusu"></td></tr>
                                <tr><td class="tabulkatd">Počet stran: </td><td><input required value="${k.pocet_stran}" type="text" onkeypress="return onlyNumbers();" name="pocet_stran"></td></tr>
                                <tr><td class="tabulkatd">Popis: </td><td><textarea required name="popis" maxlength="2550" rows="7" cols="30">${k.popis}</textarea></td></tr>
                            </table>


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
