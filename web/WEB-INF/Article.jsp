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
    <h3>by ${article.authorID}</h3>
    <p>${article.date}</p>
    <img src="${article.imageFile}"><br>
    <p>${article.content}</p>

</div>





</body>
</html>
