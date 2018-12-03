<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    </style>
</head>
<body>


<div class="header">
    <img src="https://image.ibb.co/iPXvAV/header.png"
    width="100%" height="270">

</div>

<div class="topnav">
    <a href="#">Knihy</a>
    <a href="#">Registrace</a>
    <a href="#">Login</a>
    <a href="#">Rezervace</a>
    <a href="#">Něco1</a>
    <a href="#">Něco2</a>

    <a href="#" style="float:right">Domů</a>
</div>

<div class="row">
    <div class="leftcolumn">


        <c:forEach var="k" items="${knihy}">
            <div class="card">
                <div class="btn-group">
                    <button class="button">Rezervovat</button>
                    <button class="button">Vypůjčit</button>
                </div>
            <h2><c:out value="${k.nazev }" /></h2>
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

                <c:forEach var="a" items="${autori}">
                    <tr>
                        <td>    Autori: <c:out value="${a.jmeno }" /></td>

                    </tr>

                </c:forEach>


            </div>

        </c:forEach>


    </div>
    <div class="rightcolumn">
        <div class="card">
            <h2>Adresa knihovny</h2>
            <p>Hrad I. nádvoří č. p. 1
            <br/>Hradčany
                <br/>  119 08 Praha 1,</p>
        </div>
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