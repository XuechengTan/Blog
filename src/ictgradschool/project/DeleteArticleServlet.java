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

@WebServlet(name = "DeleteArticleServlet", urlPatterns = {"/deletearticleservlet"})
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            if (req.getSession().getAttribute("loginUser")==null){
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                return;
            }

            int articleId = Integer.parseInt(req.getParameter("articleId"));
            boolean delete = ArticleDAO.deleteArticle(articleId, conn);

            if (delete) {
                System.out.println("article deleted");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/myarticleservlet");
                dispatcher.forward(req, resp);
            } else {
                System.out.println("article delete failed");
            }

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
