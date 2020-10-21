package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet(name = "ArticleServlet", urlPatterns = {"/articleservlet"})
public class ArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            String title = req.getParameter("title");
            String content = req.getParameter("article-text-area");

            String imgFileName=req.getParameter("imageFileName");
            int authorId=Integer.parseInt(req.getParameter("userID"));

            //Article article = new Article(title, content, currentDate, imgFileName, authorId);
            Article article = new Article(title, content, authorId);


            //boolean success = ArticleDAO.insertArticle(article, conn);
            boolean success = ArticleDAO.insertArticleWithoutDateAndImage(article, conn);



            if (success) {
                System.out.println("article added");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/after-submit-article.jsp");
                dispatcher.forward(req, resp);
            } else {
                System.out.println("article added failed");
            }


        } catch (SQLException  e) {

            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}


