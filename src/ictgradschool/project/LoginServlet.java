package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
//            List<FileEntry> entries = FileEntryDAO.getAllFileEntriesSorted(conn);
//            request.setAttribute("entries", entries);
            String name = request.getParameter("name");
            String password = request.getParameter("password");


            request.setAttribute("name", name);
            request.setAttribute("password", password);

            request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}