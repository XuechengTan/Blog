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

//This class is used for a user to delete any comments under their article.

@WebServlet(name = "DeleteSingleCommentServlet", urlPatterns = {"/deleteSingleCommentServlet"})
public class DeleteSingleCommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            if (req.getSession().getAttribute("loginUser")==null){
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
                return;
            }

            String content=  req.getParameter("comment");
            boolean delete = CommentDAO.deleteCommentByContent(content,conn);

            if (delete) {
                System.out.println("Comment deleted");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/myarticleservlet");
                dispatcher.forward(req, resp);
            } else {
                System.out.println("Fail");
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
