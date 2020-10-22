<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>The Pokemon Blog</title>
    <link href ="Style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Bitter&display=swap" rel="stylesheet">
</head>
<body>

<div id="header">
    <h1>The Pokemon Blog</h1>
    <div id="nav">
        <a href="../index.jsp">Home</a>
        <a href="../ArticleCreatePart.html">Add New Article</a>
        <a>Profile</a>
        <a href="../myArticle.jsp">My Articles</a>
        <a>Logout</a>
    </div>
</div>

<h1 id="title">Welcome to the Pokemon Blog!</h1>

<div id="content-wrapper">
    <p><strong>The article has been added to the blog successfully!</strong></p>
    <a href="./index.jsp">Click here to return home page</a><br>
    <a href="./myArticle.jsp">Click here to see all your articles </a>

</div>

</body>
</html>
