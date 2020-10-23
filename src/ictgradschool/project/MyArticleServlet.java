package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "MyArticleServlet", urlPatterns = {"/myarticleservlet"})
public class MyArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            if (req.getSession().getAttribute("loginUser")==null){
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                return;
            }


            User user =(User) req.getSession().getAttribute("loginUser");
            int userId = user.getUserId();
            List<Article> articles = ArticleDAO.getArticleByAuthorId(userId, conn);
            req.setAttribute("Articles", articles);


            List<Comment> comments=CommentDAO.getAllComments(conn);
            req.setAttribute("comments",comments);


         
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/myArticle.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
