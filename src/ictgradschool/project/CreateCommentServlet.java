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
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "createcomment", urlPatterns = {"/createcomment"})
public class CreateCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            String content = req.getParameter("comment-text-area");
            Date date = Date.valueOf(LocalDate.now());
            int articleId = Integer.parseInt(req.getParameter("articleId"));
            int userId=Integer.parseInt(req.getParameter("userID"));

            Comment comment = new Comment(content, date, articleId, userId);



            boolean success = CommentDAO.createComment(conn, comment);



            if (success) {
                System.out.println("comment added");
                System.out.println("articleID=" + articleId);
                System.out.println("userID=" + userId);
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
