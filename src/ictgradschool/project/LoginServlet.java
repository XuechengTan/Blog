package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            List<User> users = UserDao.getAllUsers(conn);

            String name = request.getParameter("name");
            String password = request.getParameter("password");

            for (int i = 0; i < users.size(); i++) {

                if (name.equals(users.get(i).getUserName())) {
                    byte[] salt = PasswordUtil.base64Decode(users.get(i).getSaltBase64());
                    byte[] expectedHash = PasswordUtil.base64Decode(users.get(i).getPasswordHashBase64());

                    if (PasswordUtil.isExpectedPassword(password.toCharArray(), salt, expectedHash)) {
                        request.setAttribute("name", name);
                        request.getSession().setAttribute("loginUser", users.get(i));

                        request.getRequestDispatcher("ArticleCreatePart.html").forward(request, response);
                        return;
                    }
                }
            }
            request.getRequestDispatcher("wrongInput.jsp").forward(request, response);
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
