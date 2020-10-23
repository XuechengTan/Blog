<%--
  Created by IntelliJ IDEA.
  User: jiheejoung1
  Date: 23/10/20
  Time: 8:22 PM
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



<form action="./signUp" method="post" enctype="multipart/form-data" onsubmit="return testPassword()" id="passwordForm">
    <label>Choose username:</label><br>
    <input type="text" name="userName" id="userName" value="${loginUser.userName}" onblur="testUserName()"><br><br>
    <label>Choose password:</label><br>
    <input type="password" name="password" value=""><br><br>
    <label>Re-enter password:</label><br>
    <input type="password" name="rePassword" value=""><br><br>
    <label>Enter your first name:</label><br>
    <input type="text" name ="firstName" value="${loginUser.fName}"><br><br>
    <label>Enter your surname:</label><br>
    <input type="text" name="lastName" value="${loginUser.lName}"><br><br>
    <label>Enter your date of birth:</label><br>
    <input type="date" name="dateOfBirth" value="${loginUser.dob}"><br><br>
    <label>Enter a brief description of yourself:</label><br>
    <textarea name="description" value="${loginUser.description}" rows="3" cols="50"></textarea><br><br>

    <label>Choose a profile image:</label><br>
    <input type="radio" id="avatar1" name="avatar" value="avatar1.jpg">
    <img src="images/avatar1.jpg"><br>
    <input type="radio" id="avatar2" name="avatar" value="avatar2.jpg">
    <img src="images/avatar2.jpg"><br>
    <input type="radio" id="avatar3" name="avatar" value="avatar3.jpg">
    <img src="images/avatar3.jpg"><br>
    <input type="radio" id="avatar4" name="avatar" value="avatar4.jpg">
    <img src="images/avatar4.jpg"><br><br>
    <label>Or upload a custom profile image: </label><br>
    <input type="file" id="custom-avatar" name="custom-avatar" value="" accept="image/*">


    <br><br>

    <input type="submit" value="Submit">

    </form>
</div>
</body>
</html>
