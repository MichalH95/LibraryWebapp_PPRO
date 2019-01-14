<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css"/>
    <title>Knihy</title>
</head>
<body>


<jsp:include page="common/headermenu.jsp"/>
<style>
    tr{
    background-color: gainsboro;
    }
</style>
<div class="row">
    <div class="leftcolumn">



<br/>
        <form:form action="/vyhledavani">
        <table>

            <tr><div class="dropdown">
                <button style="width:117px" class="dropbtn" type="button">Žánr</button>
                <div class="dropdown-content">
                    <select style="padding: 0;" name="zanr" size="6">
                        <option value="" selected>-</option>
                        <option value="Sci-fi">Sci-fi</option>
                        <option value="Horor">Horor</option>
                        <option value="Fantasy">Fantasy</option>
                        <option value="Detektivky">Detektivky</option>
                        <option value="Drama">Drama</option>
                        <option value="Poezie">Poezie</option>
                        <option value="Odborné">Odborné</option>
                        <option value="Pohádka">Pohádka</option>
                    </select>
                </div>
            </div>

            <div class="dropdown">
                <button style="width:113px" class="dropbtn" type="button">Jazyk</button>
                <div class="dropdown-content">
                    <select style="padding: 0;" name="jazyk" size="3">
                        <option value="" selected>-</option>
                        <option value="Čeština">Čeština</option>
                        <option value="Angličtina">Angličtina</option>
                        <option value="Němčina">Němčina</option>
                    </select>
                </div>
            </div>

            <div class="dropdown">
                <button style="width:150px" class="dropbtn" type="button">Nakladatelství</button>
                <div class="dropdown-content">
                    <select  style="padding: 0;width: 150px"name="nakladatelstvi" size="3">
                        <option value="" selected>-</option>
                        <option value="albatros">Albatros</option>
                        <option value="prometheus">Prometheus</option>
                    </select>
                </div>
            </div>

            <button class="dropbtn button" type="submit" name="druhvyhledavani" value="1">Filtrovat</button>
            <button class="dropbtn button" type="submit" name="druhvyhledavani" value="0">Zobrazit vše</button>

        </tr></table>

            <td><span style="background-color: #4CAF50; color:white; padding: 12px 16px;text-decoration: none;"> Název knihy:</span></td><input style="width: 20%;margin-left: 2;padding: 12px 16px;text-decoration: none;" type="text" name="hledani">
        </form:form>

        <jsp:include page="common/message.jsp"/>


        <%
            int priv=0;
            if (session.getAttribute("email") != null) {
                priv=(int)session.getAttribute("privilegium");
            }
        %>

<c:choose>
        <c:when test="${!empty knihy}">
    <c:forEach var="k" items="${knihy}">
            <div class="card">
                <div class="btn-group">

                    <form action="/rezervovat">
                    <button class="button" value="${k.id}" name="idecko">Rezervovat</button>
                    </form>
                    <c:choose>
                        <c:when test="${k.pocet_kusu>0}">
                            <form action="/vypujcit">
                            <button class="button" value="${k.id}" name="idecko">Vypůjčit</button>
                            </form>
                        </c:when>
                    </c:choose>



                    <%
                    if(priv==1){
                    %>
                    <form action="/editovatknihu">
                        <button style="color: red" class="button">Editovat</button>
                        <input type="hidden" name="idecko" value="${k.id }" />
                    </form>
                    <form action="/smazatknihu">
                    <button style="color: red" class="button">X</button>
                        <input type="hidden" name="idknihy" value="${k.id }" />
                    </form>

                                <% }%>
                </div>
                <h2><c:out value="${k.nazev }" />


                    <c:set var="total" value="${0}"/>
                    <c:set var="pocet" value="${0}"/>
                    <c:forEach var="r" items="${k.recenze}">
                        <c:set var="total" value="${total + r.hodnoceni}" />
                        <c:set var="pocet" value="${pocet+1}"/>
                    </c:forEach>
                    <c:set var="total" value="${total/pocet}"/>

                <span style="vertical-align: sub;color: sandybrown;font-size: 28px;">
                     <c:choose>
                        <c:when test="${pocet != 0}">
                <fmt:formatNumber value="${total}" maxFractionDigits="0" var="totals"/>
                     <c:forEach begin="1" end="${totals}">
                    *
                    </c:forEach></span>


                    <form action="/zobrazrecenzi">
                        <input type="hidden" value="${k.nazev}" name="nazevknihy">
                            <input type="submit" style="background-color: #2d7eff; padding: 0px; margin: 0;width: 5%; float: left;" value="recenze">
                    </form>
                    </c:when>
                    </c:choose>
                </h2>
                <c:choose>
                    <c:when test="${k.pocet_kusu>0}">
                     <span style="color: green"> Počet kusů: <c:out value="${k.pocet_kusu }" /></span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: red"> Počet kusů: 0</span>
                    </c:otherwise>
                </c:choose>
                <br/>
                <c:choose>
                    <c:when test="${k.pocet_kusu>0}">
                        <span style="color: green"> Počet rezervací: 0</span>
                    </c:when>
                    <c:otherwise>
                        <c:set var="rez" value="${k.pocet_kusu*-1}"/>
                        <span style="color: red"> Počet rezervací: ${rez}</span>
                    </c:otherwise>
                </c:choose>




                <table>
                    <tr>
                        <h5><td><b>Datum vydání: </b><c:out value="${k.datum_vydani }" /></td></h5>
                        <h5><td><b>Žánr: </b><c:out value="${k.zanr }"/></td></h5>
                        <h5><td><b>ISBN: </b><c:out value="${k.isbn }" /></td></h5>
                        <h5><td><b>Jazyk: </b><c:out value="${k.jazyk }"/></td></h5>
                        <h5><td><b>Nakladatelství: </b><c:out value="${k.nakladatelstvi }" /></td></h5>
                        <h5><td><b>P.Stran: </b><c:out value="${k.pocet_stran }"/></td></h5>

                    </tr>
                </table>

                <p><b>Popis: </b> <c:out value="${k.popis }" /></p>


                <c:choose>
                    <c:when test="${k.autori != null}">

                    <b>Autoři: </b>

                    <c:forEach var="a" items="${k.autori}" varStatus="loop">
                            <c:out value="${a.jmeno} (${a.vztah_ke_knize})"/>
                        <%
                            if(priv==1){
                        %>
                        <form style="display: inline" action="smazatautora">
                        <input type="hidden" name="idautora" value="${a.id}">
                        <input style="margin-left:0; padding: 0 0 0 0;color: red;width: 1%"  type="submit" value="X">
                           <% } %>
                            <c:if test="${!loop.last}">,</c:if>
                        </form>

                    </c:forEach>


                    </c:when>
                </c:choose>
            </div>
        </c:forEach>
        </c:when>
    <c:otherwise>Omlouváme se, ale nemůžeme vám nabídnout žádnou knihu</c:otherwise>
</c:choose>

    </div>
    <jsp:include page="common/rightcolumn.jsp"/>
</div>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
