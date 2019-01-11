<%@ page contentType="text/html; charset=UTF-8" %>
<div class="header">
    <img src="img/header.png"
         width="100%" height="270">

</div>

<div class="topnav">
    <a href="/">Knihy</a>
    <a href="/registrace">Registrace</a>
    <a href="/login">Login</a>
    <a href="/recenze">Recenze</a>
    <a href="/sprava">Správa</a>
    <%

        if (session.getAttribute("email") != null) {
    %>
    <a href="/logout" style="float:right;background-color: red">Logout</a>
    <%
        }
    %>
    <a href="/" style="float:right">Domů</a>

</div>