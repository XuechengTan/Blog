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

@WebServlet(name = "SingleArticleServlet", urlPatterns = {"/singlearticleservlet"})
public class SingleArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            List<Article> articles = ArticleAccessDAO.getAllArticles(conn);
            String title = req.getParameter("title");
            for (Article article: articles) {
                if(title.equals(article.getTitle())) {
                    req.setAttribute("article",article);
                }
            }
            req.setAttribute("title",title);
            req.getRequestDispatcher("WEB-INF/Article.jsp").forward(req,resp);

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