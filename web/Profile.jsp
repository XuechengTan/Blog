<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<style>
    .button {
        width: 4rem;
        height: 2rem;
        border: 1px solid;
        background: orange;
        color: #FFF;
        box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);
        border-radius: 5px;
        margin-left: 2rem;
    }
    .button:hover {
        background-color: coral;
        cursor: pointer;
    }
    .buttonDiv {
        display: flex;
    }
</style>

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
            <h1>${loginUser.userName}'s Profile</h1>

            <div id="profileImage">
                <img src="./images/${loginUser.imagePath}">
            </div>
            <div id="userDetail">
                <ul>
                    <li>Username: ${loginUser.userName}</li>
                    <li>First name: ${loginUser.fName}</li>
                    <li>Last name: ${loginUser.lName}</li>
                    <li>Date of birth: ${loginUser.dob}</li>
                </ul>
                <h3>Description</h3>
                <p>${loginUser.description}</p>
            </div>
            <div class="buttonDiv">
                <input class="button" type="submit" value="Edit" onclick="location.href = '/profile-edit.jsp';">
                <input class="button" type="button" value="Change Password" onclick="location.href = '/profile-change-password.jsp';">
                <input class="button" type="button" value="Delete" onclick="location.href = '/deleteProfileServlet';">
            </div>
        </div>
    </body>
</html>
