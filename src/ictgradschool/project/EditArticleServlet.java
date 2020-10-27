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
import java.sql.SQLException;

@WebServlet(name = "EditArticleServlet", urlPatterns = {"/editarticleservlet"})
public class EditArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            if (req.getSession().getAttribute("loginUser") == null) {
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                return;
            }

            Object articleObject = req.getSession().getAttribute("articleToModify");
            Article article = (Article) articleObject;


            String title = req.getParameter("title");
            String content = req.getParameter("article-text-area");

            article.setTitle(title);
            article.setContent(content);
            boolean edit = ArticleDAO.editArticle(article, conn);

            if (edit) {
                System.out.println("article edited");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/myArticle.jsp");
                dispatcher.forward(req, resp);
            } else {
                System.out.println("article edit failed");
            }

        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
