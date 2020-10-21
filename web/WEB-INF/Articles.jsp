<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Playtech
  Date: 21/10/2020
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href ="Style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Bitter&display=swap" rel="stylesheet">
    <script src="ReplaceLoginButtons.js"></script>
</head>
<body>

<div id="header">
    <h1>The Pokemon Blog</h1>
    <div id="nav">
        <a href="UserSignup.html">Create Account</a>
        <a href="Login.html">Login</a>
    </div>
</div>

<h1 id="title">Articles</h1>

<div id="content-wrapper">

    <c:forEach var="article" items="${articles}">
        <h2><a href="/singlearticleservlet?id=${article.articleId}">${article.title}</a> by
            <c:forEach var="user" items="${users}">
            <c:if test="${article.authorID == user.userId}">
                ${user.userName}
            </c:if>
        </c:forEach></h2><br>
    </c:forEach>


</div>



</body>
</html>
