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


        .dropbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px;
            font-size: 18px;
            border: none;
            cursor: pointer;
        }

        .dropdown {

            position: relative;
            display: inline-block;
        }

        /* Dropdown Content (Hidden by Default) */
        .dropdown-content {
            display: none;
            cursor: pointer;
            position: absolute;
            background-color: #4CAF50;

        }

        /* Links inside the dropdown */
        .dropdown-content option {
            color: white;
            border:none;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            font-size: 14px;
            background-color:#4CAF50;

        }
        select{
            overflow-y: auto;
        }

        selected {
            background-color:#2d7eff;
        }
        .dropdown-content option:hover {background-color: #28a5df; cursor:pointer;}
        .dropdown:hover .dropdown-content {
            display: block;
        }

        .button{
            background: #2d7eff;
        }
        .button:hover{
            background-color: #3e8e41;
        }

    </style>
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

<br/>
        <form:form action="/vyhledavani">
        <table><tr>
            <div class="dropdown">
                <button style="width:117px" class="dropbtn" type="button">Žánr</button>
                <div class="dropdown-content">

                    <select  name="zanr" size="6">
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
                    <select  name="jazyk" size="3">
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
                    <select  style="width: 150px"name="nakladatelstvi" size="3">
                        <option value="" selected>-</option>
                        <option value="albatros">Albatros</option>
                        <option value="prometheus">Prometheus</option>
                    </select>
                </div>
            </div>


            <button class="dropbtn button" type="submit" name="druhvyhledavani" value="1">Filtrovat</button>
            <button class="dropbtn button" type="submit" name="druhvyhledavani" value="0">Zobrazit vše</button>

        </tr></table>


        </form:form>


        <%
            int priv=0;
            if (session.getAttribute("email") != null) {
                priv=(int)session.getAttribute("privilegium");
            }
        %>

        <c:forEach var="k" items="${knihy}">
            <div class="card">
                <div class="btn-group">

                    <button class="button">Rezervovat</button>
                    <c:choose>
                        <c:when test="${k.pocet_kusu>0}">
                            <button class="button">Vypůjčit</button>
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
                <h2><c:out value="${k.nazev }" /></h2>


                <c:choose>
                    <c:when test="${k.pocet_kusu>0}">
                     <span style="color: green"> Počet kusů: <c:out value="${k.pocet_kusu }" /></span>
                    </c:when>
                    <c:otherwise>
                        <span style="color: red"> Počet kusů: <c:out value="${k.pocet_kusu }" /></span>
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

                <c:forEach var="a" items="${autori}">
                    <tr>
                        <td>    Autori: <c:out value="${a.autori.jmeno }" /></td>

                    </tr>

                </c:forEach>


            </div>

        </c:forEach>


    </div>

    <style>
        .data
        {
            width: 100%;
            height: 10%;
            background-color: red;
        }
    </style>
    <jsp:include page="common/rightcolumn.jsp"/>
</div>

<div class="footer">


    <p>Univerzita Hradec Králové - FIM UHK</p>

</div>

</body>
</html>
