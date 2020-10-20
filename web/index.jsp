<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>The Pokemon Blog</title>
    <link href ="Style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Bitter&display=swap" rel="stylesheet">
    <script src="ReplaceLoginButtons.js"></script>
</head>
<body>

<div id="header">
    <h1>The Pokemon Blog</h1>
    <div id="nav">
        <a href="UserSignup.html">Create Account</a>
        <a>Login</a>
    </div>
</div>

<h1 id="title">Welcome to the Pokemon Blog!</h1>

<div id="content-wrapper">
    <p>The Pokemon Blog is a blogging site especially for Pokemon fans! Anyone can view our amazing Pokemon bloggers, and you can create a free account and start blogging yourself.
    </p>
    <p>Another paragraph.
    </p>
    <a href="./WEB-INF/hello-world.jsp">Login</a>
    <br>
    <br>
    <a href="UserSignup.html">Create Account</a>
</div>

</body>
</html>