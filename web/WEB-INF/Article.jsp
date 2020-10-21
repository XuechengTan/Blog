<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Playtech
  Date: 21/10/2020
  Time: 9:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${article.title}</title>
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

<div id="content-wrapper">

    <h1 id="title">${article.title}</h1>
    <h3>by <c:forEach var="user" items="${users}">
        <c:if test="${article.authorID == user.userId}">
            ${user.userName}
        </c:if>
    </c:forEach>
        </h3>
    <p>${article.date}</p>
    <p>${article.content}</p>

<br><h2>Comments</h2>

<c:forEach var="comment" items="${comments}">
    <h3>
    <c:forEach var="user" items="${users}">
        <c:if test="${comment.userId == user.userId}">
            ${user.userName}
        </c:if>
    </c:forEach>
        </h3>

        <p>${comment.comment}</p>
</c:forEach>

    <br>
    <h3>Add Comment</h3>

    <form action="/createcomment" method="get">
        <label>Enter comment: </label><br>
        <textarea id="comment-text-area" name="comment-text-area" rows="4" cols="50"></textarea>
        <input type="hidden" name="articleId" value="${article.articleId}">
        <input type="hidden" name="userID" value="1"><br>
        <input type="submit" value="Post Comment"><br><br>

        for current test purposes submitting user ID always equals 1, must fix
    </form>

</div>

</body>
</html>
