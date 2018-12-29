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


                <h2>Registrace uživatele</h2>

<form:form action="/vlozituzivatele" autocomplete="on">
                <table>
                    <tr><td class="tabulkatd" >Jméno: </td><td><input required type="text" name="jmeno" placeholder="Karel" maxlength="20"></td><td class="tabulkatd" >Příjmení:</td><td><input required maxlength="20" type="text" name="prijmeni" placeholder="Vomáčka"></td></tr>
                    <tr><td class="tabulkatd" >Heslo: </td><td><input required type="password" id="pw1" maxlength="40" name="heslo1" onkeyup='check();' placeholder="tajné heslo"></td><td class="tabulkatd" >Heslo znovu:</td><td><input onkeyup='check();' required id="pw2" maxlength="40" type="password" name="heslo2" placeholder="heslo znovu" ></td></tr>
                    <tr><td class="tabulkatd" >Email: </td><td><input  required type="email" maxlength="40" name="email" placeholder="KarelVomacka@email.cz"></td><td></td><td colspan="2"><span id='message'></span></td></tr>
                    <tr><td><h3>Bydliště</h3></td></tr>
                    <tr><td class="tabulkatd" >Město: </td><td><input  required maxlength="30" type="text" name="mesto" placeholder="Praha"></td></tr>
                    <tr><td class="tabulkatd" >Ulice: </td><td><input required maxlength="40" type="text" name="ulice" placeholder="Hradčanská"></td><td class="tabulkatd" >Č. popisné:</td><td><input required maxlength="10" type="text" name="cpp" placeholder="255"></td></tr>
                    <tr><td class="tabulkatd" >PSČ: </td><td><input required type="text" maxlength="5" onkeypress="return onlyNumbers();" name="psc" placeholder="50901"></td></tr>
                    <tr><td colspan="4"><input onclick="comparePass()" type="submit" name="registrovat" value="Registrovat"></td></tr>
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
