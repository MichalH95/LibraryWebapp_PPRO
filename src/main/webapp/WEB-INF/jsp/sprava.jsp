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
    <script type="text/javascript" src="js/javascript.js"></script>
    <title>Knihy</title>
</head>

<body>

<jsp:include page="common/headermenu.jsp"/>

<div class="row">
    <div class="leftcolumn">

        <div class="card">
            <div class="container">

                <jsp:include page="common/message.jsp"/>

            <h2>Funkce admina</h2>

                <button class="adminButton" onclick="skryt()">Vložit knihu</button>
                <button class="adminButton" onclick="skryt6()">Vložit autora</button>
                <button class="adminButton" onclick="skryt1()">Spravovat rezervace</button>
                <button class="adminButton" onclick="skryt2()">Spravovat výpujčky</button>
                <button class="adminButton" onclick="skryt3()">Spravovat upomínky</button>
                <button class="adminButton" onclick="skryt4()">Spravovat uživatele</button>
                <button class="adminButton" onclick="skryt5()">Spravovat recenze</button>


                <div id="vlozknihu" style="display: none;">
                    <h2>Vložení knihy</h2>
                    <form:form action="/vlozitknihu" autocomplete="on">
                        <table>
                            <tr><td class="tabulkatd">Název knihy: </td><td><input required type="text" name="nazev"></td></tr>
                            <tr><td class="tabulkatd">Jazyk:</td><td> <select style="width: 150px"  name="jazyk">
                                <option value="Čeština" selected>Čeština</option>
                                <option value="Angličtina">Angličtina</option>
                                <option value="Němčina">Němčina</option>
                            </select></td></tr>

                            <tr><td class="tabulkatd">Žánr: </td><td><select style="width: 150px" name="zanr">
                                <option value="Sci-fi" selected>Sci-fi</option>
                                <option value="Horor">Horor</option>
                                <option value="Fantasy">Fantasy</option>
                                <option value="Detektivky">Detektivky</option>
                                <option value="Drama">Drama</option>
                                <option value="Poezie">Poezie</option>
                                <option value="Odborné">Odborné</option>
                                <option value="Pohádka">Pohádka</option>
                            </select></td></tr>

                            <tr><td class="tabulkatd">Nakladatelství:</td><td> <select style="width: 150px"  name="nakladatelstvi">
                                <option value="Albatros" selected>Albatros</option>
                                <option value="Prometheus">Prometheus</option>
                            </select></td></tr>
                            <tr><td class="tabulkatd">Datum vydání:</td><td><input  required type="date" name="datum_vydani" value="2018-07-22" min="1900-01-01" required max="2019-12-31"></td></tr>
                            <tr><td class="tabulkatd">ISBN: </td><td><input required type="text" name="isbn"></td></tr>
                            <tr><td class="tabulkatd">Počet kusů: </td><td><input required type="text" onkeypress="return onlyNumbers();" name="pocet_kusu"></td></tr>
                            <tr><td class="tabulkatd">Počet stran: </td><td><input required type="text" onkeypress="return onlyNumbers();" name="pocet_stran"></td></tr>
                            <tr><td class="tabulkatd">Popis: </td><td><textarea required name="popis" maxlength="2550" rows="7" cols="30"></textarea></td></tr>
                            <tr><td colspan="2"><input type="submit" value="Vložit knihu"></td></tr>
                        </table>
                    </form:form>
                </div>

                <div id="vlozautora" style="display: none;">
                    <h2>Vložení autora</h2>
                    <form:form action="/vlozautora" autocomplete="on">
                        <table>                        <tr><td class="tabulkatd">Kniha: </td><td><select style="width: 150px" name="knihaID">
                        <c:forEach var="n" items="${nazvyK}">
                            <option value="<c:out value="${n.id }"/>"><c:out value="${n.nazev}" /></option>
                        </c:forEach>
                        </select>
                        </td></tr>
                        <tr><td class="tabulkatd">Jméno autora:</td><td><input required type="text" name="jmeno"></td></tr>
                        <tr><td class="tabulkatd">Vztah ke knize:</td><td><input required type="text" name="vztah"></td></tr>
                        <tr><td colspan="2"><input type="submit" value="Vložit autora"></td></tr>
                        </table>
                    </form:form>
                </div>


                <div id="spravarezer" style="display: none;">
                    <h2>Správa rezervací</h2>
                    <form action="filtraceSpravyRez"><tr><td>Název knihy: </td><td><input style="width: 20%" type="text" name="nazevknihy"></td><td><input style="width:20%;margin-left: 2" type="submit" value="Vyhledat"></td></tr></form>

                    <table border="1">
                        <tr><th>Rezervováno od</th><th>Rezervováno do</th><th>Název knihy</th><th colspan="3">Akce</th></tr>
                    <c:forEach var="r" items="${rezervace}">
                        <br>
                            <tr>
                            <td>   <c:out value="${r.rezervace_od }" /></td>
                            <td>    <c:out value="${r.rezervace_do }" /></td>

                                <td>${r.kniha.nazev}</td>
                                <td><form action="/smazatrezervaci"><input type="hidden" name="idecko" value="${r.id}" /><input  style="width: 100%; margin-left: 0;margin-bottom: 0; color: red" value="X" type="submit"></form></td>
                                <td><form action="/rezervacinavypujcku"><input type="hidden" name="idecko" value="${r.id}" /><input  style="width: 100%; margin-left: 0;margin-bottom: 0" value="Převést na výpůjčku" type="submit"></form></td>
                                <td><form action="/editacerezervace"><input type="hidden" name="idecko" value="${r.id}" /><input style="width: 100%; margin-left: 0;" value="Editovat" type="submit"></form> </td>
                        </tr>
                    </c:forEach>
                    </table>
                </div>

                <div id="spravavypujcek" style="display: none;">
                    <h2>Správa výpůjček</h2>
                    <form action="filtraceSpravyVyp"><tr><td>Název knihy: </td><td><input style="width: 20%" type="text" name="nazevknihy"></td><td><input style="width:20%;margin-left: 2" type="submit" value="Vyhledat"></td></tr></form>

                    <table border="1">
                        <tr><th>Datum vypůjčení</th><th>Vypůjčeno do</th><th>Vráceno</th><th>Název knihy</th><th>Uživatel</th><th colspan="3">Akce</th></tr>
                        <c:forEach var="v" items="${vypujcky}">
                            <br>
                            <tr>
                                <td>   <c:out value="${v.datum_vypujceni }" /></td>
                                <td>    <c:out value="${v.vypujceno_do }" /></td>
                                <td>    <c:out value="${v.vraceno }" /></td>
                                <td>${v.kniha.nazev}</td>
                                <td>${v.uzivatel.email}</td>
                                <td><form action="/smazatvypujcku"><input type="hidden" name="idecko" value="${v.id}" /><input  style="width: 100%; margin-left: 0;margin-bottom: 0; color: red" value="X" type="submit"></form></td>
                                <td><form action="/editacevypujcky"><input type="hidden" name="idecko" value="${v.id}" /><input style="width: 100%; margin-left: 0;" value="Editovat" type="submit"></form> </td>
                                <td><form action="/vlozupominku">
                                    <input type="hidden" name="idvypujcky" value="${v.id}" />
                                    <input type="hidden" name="idknihy" value="${v.kniha.id}" />
                                    <input type="hidden" name="iduzivatele" value="${v.uzivatel.id}" />
                                    <input style="width: 100%; margin-left: 0;" value="Upomínka" type="submit"></form> </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div id="spravaupominek" style="display: none;">
                    <h2>Sprava upomínek</h2>
                    <form action="filtraceSpravyUpo"><tr><td>Email uživatele: </td><td><input style="width: 20%" type="text" name="emailuzivatele"></td><td><input style="width:20%;margin-left: 2" type="submit" value="Vyhledat"></td></tr></form>

                    <table border="1">
                        <tr><th>Pokuta</th><th>Uživatel</th><th>Datum vrácení</th><th>Popis</th><th colspan="2">Akce</th></tr>
                        <c:forEach var="u" items="${upominky}">
                            <br>
                            <tr>
                                <td><c:out value="${u.pokuta }" /> Kč</td>
                                <td>${u.uzivatel.email}</td>
                                <td>${u.vypujcka.vypujceno_do}</td>
                                <td><c:out value="${u.popis }" /></td>



                                <td><form action="/smazatupominku"><input type="hidden" name="idecko" value="${u.id}" /><input  style="width: 100%; margin-left: 0;margin-bottom: 0; color: red" value="X" type="submit"></form></td>

                                <td><form action="/editaceupominky"><input type="hidden" name="idecko" value="${u.id}" /><input style="width: 100%; margin-left: 0;" value="Editovat" type="submit"></form> </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div id="spravauzivatele" style="display: none;">
                    <h2>Sprava uživatelů</h2>
                    <form action="filtraceSpravyUzi"><tr><td>Email uživatele: </td><td><input style="width: 20%" type="text" name="emailuzivatele"></td><td><input style="width:20%;margin-left: 2" type="submit" value="Vyhledat"></td></tr></form>

                    <table border="1">
                        <tr><th>Uživatel</th><th>blokace</th><th colspan="3">Akce</th></tr>
                        <c:forEach var="u" items="${uzivatele}">

                            <tr>
                                <td>   <c:out value="${u.email }" /></td>
                                                          <td>${u.blokace}</td>

                               <c:choose>
                                   <c:when test="${u.privilegia!=1}">
                                   <td><form action="/smazatuzivatele"><input type="hidden" name="idecko" value="${u.id}" /><input  style="width: 100%; margin-left: 0;margin-bottom: 0; color: red" value="X" type="submit"></form></td>
                                   <td><form action="/blokovatuzivatele"><input type="hidden" name="idecko" value="${u.id}" /><input  style="width: 100%; margin-left: 0;margin-bottom: 0" value="Blokovat" type="submit"></form></td>
                                   <td><form action="/odblokovatuzivatele"><input type="hidden" name="idecko" value="${u.id}" /><input  style="width: 100%; margin-left: 0;margin-bottom: 0" value="Odblokovat" type="submit"></form></td>
                                   </c:when>
                               </c:choose>

                            </tr>
                        </c:forEach>
                    </table>


                </div>

                <div id="spravarecenze" style="display: none;">
                    <h2>Správa recenze</h2>
                    <form action="filtraceSpravyRec"><tr><td>Název knihy: </td><td><input style="width: 20%" type="text" name="nazevknihy"></td><td><input style="width:20%;margin-left: 2" type="submit" value="Vyhledat"></td></tr></form>

                    <table border="1">
                        <tr><th>Uživatel</th><th>Hodnocení</th><th>Recenze</th><th>Kniha</th><th>Akce</th></tr>
                        <c:forEach var="r" items="${recenze}">

                            <tr>
                                <td>   <c:out value="${r.jmeno_autora }" /></td>
                                <td>   <c:out value="${r.hodnoceni }" /></td>
                                <td>   <c:out value="${r.recenze }" /></td>
                                <td>${r.kniha.nazev}</td>
                                <td><form action="/smazatrecenzi"><input type="hidden" name="idecko" value="${r.id}" /><input  style="width: 100%; margin-left: 0;margin-bottom: 0; color: red" value="X" type="submit"></form></td>

                            </tr>
                        </c:forEach>
                    </table>

                </div>


<hr>
                <form:form action="/logout">
                    <input style="background-color: red" type="submit" name="login" value="Logout">
                </form:form>



            </div>
        </div>

    </div>
    <jsp:include page="common/rightcolumn.jsp"/>
</div>

<jsp:include page="common/footer.jsp"/>

</body>
</html>
