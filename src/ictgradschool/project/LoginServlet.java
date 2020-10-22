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


@WebServlet(name = "LoginServlet", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            List<User> users = UserDao.getAllUsers(conn);

            String name = request.getParameter("name");
            String password = request.getParameter("password");
            byte[] hashGenerated = PasswordUtil.insecureHash(password);
            String hash = PasswordUtil.base64Encode(hashGenerated);

            for (int i = 0; i <users.size() ; i++) {
                System.out.println(users.get(i).getUserName());
                if (name.equals(users.get(i).getUserName())){

                    if (hash.equals(users.get(i).getPasswordHashBase64())){
                        request.setAttribute("name", name);


                        // Need to finish code to create the Session
                        // And to create a session-id cookie
                        request.getSession().setAttribute("loginUser", users.get(i));

                        request.getRequestDispatcher("ArticleCreatePart.html").forward(request, response);
                        return;
                    }else {
                        request.getRequestDispatcher("wrongInput.jsp").forward(request, response);
                        return;
                    }

                    }

            }

//



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
