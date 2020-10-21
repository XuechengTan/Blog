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

@WebServlet(name = "EditArticleButtonServlet", urlPatterns = {"/editarticlebuttonservlet"})
public class EditArticleButtonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            int articleId = Integer.parseInt(req.getParameter("articleId"));

            Article article = ArticleDAO.getArticleByArticleId(articleId, conn);
            req.getSession().setAttribute("articleToModify", article);
            req.setAttribute("modifyContent",article.getContent());
            req.setAttribute("title",article.getTitle());

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editArticlePage.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);

        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
