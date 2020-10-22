<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>My Article</title>
    <meta charset="UTF-8">
    <title>The Pokemon Blog</title>
    <link href="Style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css2?family=Bitter&display=swap" rel="stylesheet">
    <style>
        .myArticleDiv {

            width: 95rem;
            min-height: 18rem;
            box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);
            border-top: 10px solid orange;
            border-radius: 6px;
            margin: 2rem;
        }

        .editButton {
            width: 4rem;
            height: 2rem;
            border: 1px solid;
            background: orange;
            color: #FFF;
            box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);
            border-radius: 5px;
            margin-left: 2rem;
        }

        .deleteButton {
            width: 4rem;
            height: 2rem;
            border: 1px solid;
            background: orange;
            color: #FFF;
            box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);
            border-radius: 5px;
            margin-left: 2rem;
        }

        .deleteButton:hover {
            background-color: coral;
            cursor: pointer;
        }

        .editButton:hover {
            background-color: coral;
            cursor: pointer;
        }

        .buttonDiv {
            display: flex;
        }

        #content-wrapper {
            display: flex;
            flex-direction: column;
        }

        .comment.deleteButton {
            width: 4rem;
            height: 2rem;
            border: 1px solid;
            background: orange;
            color: #FFF;
            box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);
            border-radius: 5px;
            margin-left: 2rem;
            margin-right: 2rem;
        }

        .comment.deleteButton:hover {
            background-color: coral;
            cursor: pointer;
        }

        .commentDeleteAndDate {
            display: flex;
            justify-content: flex-end;
        }

        .singleComment {
            margin-right: 2rem;
        }



        .short{
            height: 0;
            overflow : hidden;

        }
        .all{
            height: 100%;
            overflow : hidden;
        }

        .showButton{
            width: 100%;
            height: 3rem;
            border: 1px solid;
            background: orange;
            color: #FFF;
            box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);
            border-radius: 5px;
            text-align: center;
            margin: auto;
        }
        .showButton:hover{
            background-color: coral;
            cursor: pointer;
        }



    </style>

</head>

<body>

<div id="header">
    <h1>The Pokemon Blog</h1>
    <div id="nav">
        <a href="index.jsp">Home</a>
        <a>Add New Article</a>
        <a>Profile</a>
        <a>My Articles</a>
        <a>Logout</a>
    </div>
</div>

<h1 id="title">My Articles</h1>

<div id="content-wrapper">


    <c:forEach var="article" items="${Articles}">
        <div class="myArticleDiv">
            <h1>${article.title}</h1>
            <p><strong>date: </strong><fmt:formatDate value="${article.date}" type="both"
                                                      pattern="yyyy-MM-dd HH:mm:ss"/></p>
            <p>content:</p>

            <div class="con">
                <div class="short">
                    <div class="many-txt">${article.content}</div>
                </div>
                <button class="showButton" type="button">Show whole article</button>
            </div>


            <p><strong>Comment: </strong></p>
            <ul>
                <c:forEach var="comment" items="${comments}">
                    <c:if test="${article.articleId == comment.articleId}">
                        <li>${comment.comment} </li>
                        <br>
                        <div class="commentDeleteAndDate">
                            <p class="singleComment">created by ${comment.userId} at <fmt:formatDate
                                    value="${comment.date}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                            <input class="comment deleteButton" type="button" value="Delete">
                        </div>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
        <div class="buttonDiv">

            <input class="editButton" data-articleid="${article.articleId}" type="button" value="Edit">
            <form class="edit${article.articleId}" action="/editarticlebuttonservlet" method="get">
                <input type="hidden" name="articleId" value="${article.articleId}">
            </form>

            <input class="deleteButton" data-articleid="${article.articleId}" type="button" value="Delete">
            <form class="delete${article.articleId}" action="/deletearticleservlet" method="get">
                <input type="hidden" name="articleId" value="${article.articleId}">
            </form>

        </div>
    </c:forEach>


</div>

<div class="testButtonDiv">
    <form class="testButtonForm" action="/myarticleservlet" method="get">

    </form>
    <input id="testButton" type="button" value="test display the user articles">
</div>

</body>
<script>

    const button = document.getElementById("testButton");
    const form = document.getElementsByClassName("testButtonForm")[0];
    button.addEventListener('click', function () {
        form.submit();
    })

    const editButton = document.querySelectorAll(".editButton");
    for (let i = 0; i < editButton.length; i++) {
        let button = editButton[i];

        button.addEventListener('click', function (e) {

            let articleId = this.dataset.articleid;
            let editForm = document.getElementsByClassName("edit" + articleId)[0];
            editForm.submit();
        });
    }

    const deleteButtons = document.querySelectorAll('.deleteButton');
    for (let i = 0; i < deleteButtons.length; i++) {
        let button = deleteButtons[i];

        button.addEventListener('click', function (e) {

            let articleId = this.dataset.articleid;
            let deleteForm = document.getElementsByClassName("delete" + articleId)[0];
            deleteForm.submit();
        });
    }


    const showButtons = document.getElementsByClassName("showButton");
    let p = document.querySelectorAll(".con .short");

    for(let i=0; i<showButtons.length; i++) {
        showButtons[i].onclick = function () {
            if(this.innerHTML === "Show whole article"){
                p[i].classList.remove("short");
                p[i].classList.add("all");
                this.innerHTML = "Pack up";
            }
            else{
                p[i].classList.remove("all");
                p[i].classList.add("short");
                this.innerHTML = "Show whole article";
            }
        }
    }

</script>
</html>
