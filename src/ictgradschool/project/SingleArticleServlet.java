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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SingleArticleServlet", urlPatterns = {"/singlearticleservlet"})
public class SingleArticleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            List<Article> articles = ArticleAccessDAO.getAllArticles(conn);
            int id = Integer.parseInt(req.getParameter("id"));
            for (Article article: articles) {
                if(id == (article.getArticleId())) {
                    req.setAttribute("article",article);
                }
            }

            List<User> users = UserDao.getAllUsernameAndID(conn);

            //gets all comments and checks if any are comments on this article
            List<Comment> comments = CommentDAO.getAllComments(conn);
            List<Comment> thisArticleComments = new ArrayList<>();
            for (Comment comment: comments) {
                if (id == comment.articleId) {
                    thisArticleComments.add(comment);
                }
            }

            req.setAttribute("users",users);
            req.setAttribute("comments",thisArticleComments);
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