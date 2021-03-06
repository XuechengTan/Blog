<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Edit Article Page</title>
    <link rel="stylesheet" type="text/css" href="Style.css">
    <style>
        .editor {
            width: 40rem;
            min-height: 18rem;
            box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);
            border-top: 6px solid orange;
            border-radius: 3px;
            margin: 2rem;
        }

        .editor .toolbar {
            box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2);
        }

        .editor .toolbar .line {
            display: flex;
            border-bottom: 1px solid #e2e2e2;
        }

        .editor .toolbar .line .box {
            display: flex;
            border-left: 1px solid #e2e2e2;
        }

        .editor .toolbar .line .box .btn {
            display: block;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            transition: 0.2s ease all;
        }

        .editor .toolbar .line .box .btn:hover {
            background-color: #e1e1e1;
            cursor: pointer;
        }

        .editor .toolbar .line .box .btn img {
            width: 15px;
            padding: 10px;
        }

        .editor .content-area {
            padding: 15px 12px;
            line-height: 1.5;
        }

        .editor .content-area .view {
            outline: none;
        }

        .editor .content-area .view p {
            margin: 12px 0;
        }

        .submitBtnClass {
            margin-left: 2rem;
        }

        .submitBtn {
            width: 150px;
            height: 40px;
            border: 1px solid;
            background: orange;
            color: #FFF;
            box-shadow: 0 0 4px 1px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
        }

        .submitBtn:hover {
            background-color: coral;
            cursor: pointer;
        }

        .submittedArticle {
            margin-left: 2rem;
        }

        #article-text {
            display: none;
        }

        .editor .toolbar .line .box .btn.has-submenu {


        }

        .editor .toolbar .line .box .btn.has-submenu .submenu {
            display: none;
            position: absolute;
            top: 36px;
            left: -1px;
            z-index: 10;
            background-color: #fff;
            border: 1px solid #b5b5b5;
            border-top: none;
        }

        .editor .toolbar .line .box .btn.has-submenu .submenu .btn {
            width: 80px;

        }

        .editor .toolbar .line .box .btn.has-submenu .submenu .btn img {
            width: 60px;
            height: 20px;
        }

        .editor .toolbar .line .box .btn.has-submenu:hover .submenu {
            display: block;
        }
    </style>
</head>
<body>
<div id="header">
    <h1>Article edit</h1>
</div>
<div id="content-wrapper">
    <form id="articleForm" action="./editarticleservlet" method="get">
        <label><strong>You can modify your article title</strong></label><br>
        <input type="text" name="title" style="width:20vw;"value="${title}"><br>
        <label><strong>Please modify your article</strong></label><br>
        <textarea id="article-text" name="article-text-area"></textarea>
        <input type="hidden" name="currentDate" value="">
        <input type="hidden" name="userID" value="1">
        <input type="hidden" name="imageFileName" value="">

    </form>



    <div class="editor">
        <div class="toolbar">
            <div class="line">
                <div class="box">
            <span class="btn" data-action="bold" title="Bold">
          <img src="./images/editor/Bold.svg">
        </span>
                    <span class="btn" data-action="italic" title="Italic">
          <img src="./images/editor/Italic.svg">
        </span>
                    <span class="btn" data-action="underline" title="Underline">
          <img src="./images/editor/Underline.svg">
        </span>
                </div>

                <div class="box">
            <span class="btn" data-action="justifyLeft" title="Justify Left">
          <img src="./images/editor/justifyLeft.svg">
            </span>
                    <span class="btn icon" data-action="justifyCenter" title="Justify center">
              <img src="./images/editor/justifyCenter.svg">
            </span>
                    <span class="btn icon" data-action="justifyRight" title="Justify right">
              <img src="./images/editor/justifyRight.svg">
            </span>
                </div>

                <div class="box">
                    <span class="btn has-submenu"><p><strong>Back Color</strong><p>
    <div class="submenu">
                    <span class="btn" data-action="backColor" data-name="red" title="Red">
          <img src="./images/editor/red.gif">
            </span>
      <span class="btn" data-action="backColor" data-name="orange" title="Orange">
          <img src="images/editor/orange.gif">
            </span>
                        <span class="btn" data-action="backColor" data-name="yellow" title="Yellow">
          <img src="images/editor/yellow.gif">
            </span>
                        <span class="btn" data-action="backColor" data-name="blue" title="Blue">
          <img src="images/editor/blue.gif">
            </span>
                        <span class="btn" data-action="backColor" data-name="green" title="Green">
          <img src="images/editor/green.gif">
            </span>
                        <span class="btn" data-action="backColor" data-name="cyan" title="Cyan">
          <img src="images/editor/cyan.gif">
            </span>
                        <span class="btn" data-action="backColor" data-name="purple" title="Purple">
          <img src="images/editor/purple.gif">
            </span>
                        <span class="btn" data-action="backColor" data-name="black" title="Black">
          <img src="images/editor/black.gif">
            </span>
                        <span class="btn" data-action="backColor" data-name="white" title="White">
          <img src="images/editor/white.gif">
            </span>
</div>
</span>
                </div>

                <div class="box">
                    <span class="btn has-submenu"><p><strong>Font Size</strong><p>
    <div class="submenu">
                    <span class="btn" data-action="fontSize" data-name="1" title="Size 1">
                       <img src="images/editor/size1.SVG" alt="Size 1">
            </span>
                            <span class="btn" data-action="fontSize" data-name="2" title="Size 2">
          <img src="images/editor/size2.SVG" alt="Size 2">
            </span>
                            <span class="btn" data-action="fontSize" data-name="3" title="Size 3">
         <img src="images/editor/size3.SVG" alt="Size 3">
            </span>
                            <span class="btn" data-action="fontSize" data-name="4" title="Size 4">
          <img src="images/editor/size4.SVG" alt="Size 4">
            </span>
                            <span class="btn" data-action="fontSize" data-name="5" title="Size 5">
          <img src="images/editor/size5.SVG" alt="Size 5">
            </span>
                            <span class="btn" data-action="fontSize" data-name="6" title="Size 6">
          <img src="images/editor/size6.SVG" alt="Size 6">
            </span>
                            <span class="btn" data-action="fontSize" data-name="7" title="Size 7">
          <img src="images/editor/size7.SVG" alt="Size 7">
            </span>

</div>
</span>
                </div>

            </div>


            <div class="line">
                <div class="box">
        <span class="btn" data-action="undo" title="Undo">
          <img src="./images/editor/Undo.svg">
        </span>
                    <span class="btn" data-action="removeFormat" title="Remove format">
          <img src="./images/editor/removeFormat.svg">
        </span>
                </div>
            </div>
        </div>
        <div class="content-area">

            <div class="view" contenteditable>
                ${modifyContent}
            </div>

        </div>

    </div>
    <div class="submitBtnClass">
        <input class="submitBtn" type="button" value="Submit your article"><br>
    </div>
</div>
</body>
<script>
    const editor = document.getElementsByClassName('editor')[0];
    const toolbar = editor.getElementsByClassName('toolbar')[0];
    const buttons = toolbar.querySelectorAll('.btn:not(.has-submenu)');
    for (let i = 0; i < buttons.length; i++) {
        let button = buttons[i];
        button.addEventListener('click', function (e) {
            let action = this.dataset.action;

            if (action === 'backColor') {
                let name = this.dataset.name;

                document.execCommand(action, false, name);
            } else if (action === 'fontSize') {
                let name = this.dataset.name;
                switch (name) {
                    case("1"):
                        document.execCommand(action, false, 1);
                        break;
                    case('2'):
                        document.execCommand(action, false, 2);
                        break;
                    case('3'):
                        document.execCommand(action, false, 3);
                        break;
                    case('4'):
                        document.execCommand(action, false, 4);
                        break;
                    case('5'):
                        document.execCommand(action, false, 5);
                        break;
                    case('6'):
                        document.execCommand(action, false, 6);
                        break;
                    case('7'):
                        document.execCommand(action, false, 7);
                        break;
                }
            } else {
                document.execCommand(action, false);
            }
        });
    }
    const submitArticles = document.getElementsByClassName('submittedArticle')[0];
    const subBut = document.getElementsByClassName('submitBtn')[0];
    const content = document.getElementById('article-text');
    const view = document.getElementsByClassName('view')[0];
    subBut.addEventListener('click', function () {

        content.value = view.innerHTML;

        document.getElementById('articleForm').submit();
    })
</script>
</html>
