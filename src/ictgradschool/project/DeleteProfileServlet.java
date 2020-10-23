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
@WebServlet(name = "deleteProfileServlet",  urlPatterns = {"/deleteProfileServlet"} )
public class DeleteProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            User userSession =(User)req.getSession().getAttribute("loginUser");
            int userId = userSession.getUserId();
            boolean delete = UserDao.deleteUser(userId, conn);

            if (delete) {
                System.out.println("User deleted");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
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
