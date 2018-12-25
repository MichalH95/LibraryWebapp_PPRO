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
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        input[type=password], select, textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        input[type=text], select, textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .tabulkatd
        {
            padding: 8px;
            float:right;
        }

    </style>

        <script language="JavaScript">
            function onlyNumbers(evt)
            {
                var e = event || evt; // for trans-browser compatibility
                var charCode = e.which || e.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57))
                    return false;
                return true;
            }

            var check = function() {
                if (document.getElementById('pw1').value ==
                    document.getElementById('pw2').value) {
                    document.getElementById('message').style.color = 'green';
                    document.getElementById('message').innerHTML = 'Hesla se shodují';
                } else {
                    document.getElementById('message').style.color = 'red';
                    document.getElementById('message').innerHTML = 'Hesla se neshodují';
                }
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
                    <tr><td colspan="3"><input onclick="comparePass()" type="submit" name="registrovat" value="Registrovat"></td></tr>
                </table>
</form:form>

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
