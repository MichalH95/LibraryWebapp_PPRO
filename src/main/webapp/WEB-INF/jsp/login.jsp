<%@ page import="com.ppro.projekt.web.LoginController" %>
<%@ page import="com.ppro.projekt.service.SpravaDb" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Knihy</title>

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: Arial;
            padding: 10px;
            background-image: url("https://image.ibb.co/eSQtxA/2c263f13.png");
        }

        /* Header/Blog Title */
        .header {
            padding: 0px;
            text-align: center;
            background: white;
        }

        .header h1 {
            font-size: 50px;
        }

        /* Style the top navigation bar */
        .topnav {
            overflow: hidden;
            background-color: #4CAF50;
        }

        /* Style the topnav links */
        .topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            font-size: 20px;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        /* Change color on hover */
        .topnav a:hover {
            background-color: #3e8e41;
            color: white;
        }

        /* Create two unequal columns that floats next to each other */
        /* Left column */
        .leftcolumn {
            float: left;
            width: 75%;
        }

        /* Right column */
        .rightcolumn {
            float: left;
            width: 25%;
            background-image: url("https://image.ibb.co/eSQtxA/2c263f13.png");
            padding-left: 20px;
        }

        /* Add a card effect for articles */
        .card {
            background-color: white;
            padding: 20px;
            margin-top: 20px;
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* Footer */
        .footer {
            background:#4CAF50;
            text-align: center;
            margin-top: 15px;
            padding:3px;
            color:white;
            font-size:20px;
        }

        /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 800px) {
            .leftcolumn, .rightcolumn {
                width: 100%;
                padding: 0;
            }
        }

        /* Responsive layout - when the screen is less than 400px wide, make the navigation links stack on top of each other instead of next to each other */
        @media screen and (max-width: 400px) {
            .topnav a {
                float: none;
                width: 100%;
            }
        }
        .btn-group .button {
            background-color: #4CAF50; /* Green */
            border: 1px solid green;
            color: white;
            padding: 10px 27px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            cursor: pointer;
            float: right;
        }
        .btn-group .button:not(:last-child) {
            border-right: none; /* Prevent double borders */
        }
        .btn-group .button:hover {
            background-color: #3e8e41;
        }
td{
    padding-left: 5px;

}

table{
    width: 90%;
    padding: 5px;

}

        input[type=email], select, textarea {
            width: 50%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        input[type=password], select, textarea {
            width: 50%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        input[type=text], select, textarea {
            width: 50%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        input[type=date], select, textarea {
            width: 50%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        resize: none;
        }

        input[type=submit] {
             background-color: #4CAF50;
             color: white;
             width:30%;
             padding: 12px 20px;
             border: none;
             border-radius: 4px;
             cursor: pointer;
             margin-left: 27%;

         }

        input[type=button] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 5%;
            width: 10%;

        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .tabulkatd
        {
            padding: 8px;
            float:right;
        }

        textarea {
            resize: none;
        }

    </style>

    <script>

        function skryt()
        {
            var x = document.getElementById("myDIV");
            if (x.style.display === "none") {
                x.style.display = "block";
            }else{
                x.style.display = "none"
            }
        }

        function onlyNumbers(evt)
        {
            var e = event || evt; // for trans-browser compatibility
            var charCode = e.which || e.keyCode;
            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
            return true;
        }
    </script>

</head>
<body>


<div class="header">
    <img src="https://image.ibb.co/iPXvAV/header.png"
    width="100%" height="270">

</div>

<div class="topnav">
    <a href="/">Knihy</a>
    <a href="/registrace">Registrace</a>
    <a href="/login">Login</a>
    <a href="/rezervace">Rezervace</a>
    <a href="/sprava">Správa</a>
    <a href="/" style="float:right">Domů</a>
</div>

<div class="row">
    <div class="leftcolumn">

        <div class="card">
            <div class="container">


                <%

                    if (session.getAttribute("email") == null) {
                        %>
                <h2>Přihlášní do knihovny</h2>


                <form:form action="/overlogin" autocomplete="on">
                    <table>
                        <tr><td class="tabulkatd" >Email: </td><td><input required type="email" name="email" maxlength="50"></td></tr>
                        <tr><td class="tabulkatd" >Heslo: </td><td><input required type="password" maxlength="40" name="heslo" ></td></tr>
                        <tr><td colspan="3"><input type="submit" name="login" value="Login"></td></tr>
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
    <% if(priv==1){%>

            <br>
            <h2>Funkce admina</h2>
            <input type="button" onclick="skryt()" value="Vložit knihu">
                <div id="myDIV" style="display: none;">


                    <h1>Vložení knihy</h1>

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
                            <tr><td class="tabulkatd">Počet stran: </td><td><input required type="text" onkeypress="return onlyNumbers();" name="pocet_stran"></td></tr>
                            <tr><td class="tabulkatd">Popis: </td><td><textarea required name="popis" maxlength="2550" rows="7" cols="30"></textarea></td></tr>
                            <tr><td colspan="2"><input type="submit" value="Vložit knihu"></td></tr>
                        </table>
                    </form:form>

                </div>
      <% }%>



                <h1>Vaše výpůjčky</h1>
                <table border="2">
                    <tr><td>Datum vypůjčení</td><td> Vypůjčeno do</td><td> Vráceno</td> <td>Název knihy</td></tr>





                <c:forEach var="v" items="${vypujcky}">

                    <tr>
                        <td>    <c:out value="${v.datum_vypujceni }" /></td>
                        <td>    <c:out value="${v.vypujceno_do }" /></td>

                        <c:choose>
                            <c:when test="${v.vraceno==true}">
                                <td>Ano</td>
                                <br />
                            </c:when>
                            <c:otherwise>
                                <td>Ne</td>
                                <br />
                            </c:otherwise>
                        </c:choose>
                        <td>${v.kniha.nazev}</td>

                    </tr>
                </table>
                </c:forEach>

                <form:form action="/logout">
                    <input type="submit" name="login" value="Logout">
                </form:form>

                    <%}
                %>



            </div>
        </div>

    </div>
    <div class="rightcolumn">

        <div class="card">
            <h3>Nejnovější knihy</h3>
            <p><a href="#">Harry Potter a Kámen mudrců</a> - 21.11.2018</p>
            <p><a href="#">Dívka ve vlaku</a> - 20.11.2018</p>
          </div>
        <div class="card">
            <h3>Vytvořil</h3>
            <p>Dominik Špinka</p>
            <p>Michael Húževka</p>
        </div>
    </div>
</div>

<div class="footer">


<p>Univerzita Hradec Králové - FIM UHK</p>

</div>

</body>
</html>
