<%--
  Created by IntelliJ IDEA.
  User: jiheejoung1
  Date: 26/10/20
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Title</title>
    <link href ="Style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Bitter&display=swap" rel="stylesheet">
    <script src="ReplaceLoginButtons.js"></script>
    <script src="checkInput.js"></script>

</head>

<body>
    <div id="header">
        <h1>The Pokemon Blog</h1>
        <div id="nav">
            <a href="index.jsp">Home</a>
            <a href="ArticleCreatePart.html">Add New Article</a>
            <a href="Profile.jsp">Profile</a>
            <a href="myArticle.jsp">My Articles</a>
            <a>Logout</a>
        </div>
    </div>
    <div id="content-wrapper">
        <h1>Edit Profile</h1>

        <form action="./editPassword" method="post" onsubmit="">
            <label>Enter your old password:</label><br>
            <input type="password" name="oldPassword" value=""><br><br>
            <label>Choose new password:</label><br>
            <input type="password" name="password" value=""><br><br>
            <label>Re-enter new password:</label><br>
            <input type="password" name="rePassword" value="" onblur="return testPassword()"><br><br>

            <input type="submit" value="Submit">
        </form>
    </div>
</body>

</html>
