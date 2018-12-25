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

        .adminButton {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

    </style>

    <script>

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
    <a href="/sprava">Správa</a>
    <a href="/" style="float:right">Domů</a>
</div>

<div class="row">
    <div class="leftcolumn">

        <div class="card">
            <div class="container">
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
    <div class="rightcolumn">
        <div class="card">
            <h2>Adresa knihovny</h2>
            <p>Hrad I. nádvoří č. p. 1
                <br/>Hradčany
                <br/>  119 08 Praha 1,</p>
        </div>


        <div class="card">
            <h3>Kontakty: </h3>
            <p>Telefon:	221 663 111 (ústředna)</p>
            <p>Elektronická podatelna: posta@uhk.cz</p>
            <p>Datová  schránka:	5qt8sy8</p>
            <p>IČO:	00023221</p>
            <p>DIČ:CZ00023221</p>
            <p>Facebook: www.facebook.com/knihovna</p>
        </div>


        <div class="card">
            <h3>Vytvořil</h3>
            <p>Dominik Špinka</p>
            <p>Michal Húževka</p>
            <form action="/nahratdata">
                <input class="data" type="submit" value="Nahrát data DB">
            </form>
        </div>
    </div>
</div>

<div class="footer">


    <p>Univerzita Hradec Králové - FIM UHK</p>

</div>

</body>
</html>
