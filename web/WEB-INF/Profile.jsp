<%--
  Created by IntelliJ IDEA.
  User: jiheejoung1
  Date: 22/10/20
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
            <h1 id="username">${user.userName}</h1>
            <div id="profileImage">

            </div>
            <div id="userDetail">
                ${user.fName}
                ${user.lName}
                ${user.dob}
                ${user.description}
            </div>
        </div>
    </body>
</html>
