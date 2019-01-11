<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <link rel="stylesheet" href="css/style.css"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Knihy</title>
</head>
<body>


<jsp:include page="common/headermenu.jsp"/>
<style>
    td{
    border: 1px solid black;
        padding-top: 5px;
        padding-bottom: 5px;
        font-size:15px;
    }
</style>
<div class="row">
    <div class="leftcolumn">

        <div class="card">
            <div class="container">

                <jsp:include page="common/message.jsp"/>

                <%

                    if (session.getAttribute("email") == null) {
                        %>
                <h2>Přihlášení do knihovny</h2>


                <form:form action="/overlogin" autocomplete="on">
                    <table>
                        <tr><td class="tabulkatd" style="border: none" >Email: </td><td style="border: none"><input style="width: 50%" required type="email" name="email" maxlength="50"></td></tr>
                        <tr><td class="tabulkatd" style="border: none">Heslo: </td><td style="border: none"><input style="width: 50%;" required type="password" maxlength="40" name="heslo" ></td></tr>
                        <tr><td colspan="3" style="border: none"><input type="submit" name="login" value="Login"></td></tr>
                    </table>
                </form:form>
         <%
                    }else
                    {%>

                Vítej <%
                String name=(String)session.getAttribute("email");
                int priv=(int)session.getAttribute("privilegium");
                out.print(name);

            %>! jsi přihlášen.

                <h1>Vaše výpůjčky</h1>
                <c:choose>
                    <c:when test="${not empty vypujcky}">
                        <table>
                            <tr><th>Datum vypůjčení</th><th> Vypůjčeno do</th><th> Vráceno</th> <th>Název knihy</th><th>Recenze</th></tr>

                            <c:forEach var="v" items="${vypujcky}">

                                <tr>
                                    <td>    <c:out value="${v.datum_vypujceni }" /></td>
                                    <td>    <c:out value="${v.vypujceno_do }" /></td>

                                    <c:choose>
                                        <c:when test="${v.vraceno==true}">
                                            <td>Ano</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Ne</td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td>${v.kniha.nazev}</td>
                                    <form action="/napsatrecenzi">
                                        <td style="padding:0 0 0 0;background-color: #4CAF50"><input type="hidden" name="idknihy" value="${v.kniha.id}">
                                            <input style="width: 100%;height: 10%; margin-left: 0" type="submit" value="Napsat"></td>
                                    </form>
                                </tr>
                            </c:forEach>
                        </table>

                    </c:when>
                    <c:otherwise>
                        <td>Doposud jste si nevypůjčil žádnou knihu.</td>
                    </c:otherwise>
                </c:choose>


                <h1>Vaše upomínky</h1>
                <c:choose>
                    <c:when test="${not empty upominky}">
                        <table>
                            <tr><th class="upominky">Pokuta</th><th class="upominky">Popis</th><th class="upominky">Název knihy</th><th class="upominky">Datum vrácení</th></tr>
                            <c:forEach var="u" items="${upominky}">
                                <tr>

                                    <td>    <c:out value="${u.pokuta }" /> Kč</td>
                                    <td>    <c:out value="${u.popis }" /></td>
                                    <td>    <c:out value="${u.kniha.nazev }" /></td>
                                    <td>    <c:out value="${u.vypujcka.vypujceno_do }" /></td>
                                </tr>


                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <td>Na Váš účet nebyla doposud uložena upomínka.</td>
                    </c:otherwise>
                </c:choose>

                <h1>Vaše rezervace</h1>
                <c:choose>
                    <c:when test="${not empty rezervace}">
                        <table>
                            <tr><th class="rezervace">Rezervace do</th><th class="rezervace">Název knihy</th></tr>
                            <c:forEach var="r" items="${rezervace}">

                                <tr>

                                    <td>    <c:out value="${r.rezervace_do }" /></td>
                                    <td>    <c:out value="${r.kniha.nazev }" /></td>
                                </tr>


                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <td>Nemáte žádné rezervace.</td>
                    </c:otherwise>
                </c:choose>


                <br>
                <form:form action="/logout">
                    <input style="background-color: red" type="submit" name="login" value="Logout">
                </form:form>

                    <%}
                %>



            </div>
        </div>

    </div>
    <jsp:include page="common/rightcolumn.jsp"/>
</div>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
