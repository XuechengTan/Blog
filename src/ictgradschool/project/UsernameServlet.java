package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.JSONResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
@WebServlet(name = "usernames",  urlPatterns = {"/usernames"} )
public class UsernameServlet extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            List<User> userList = UserDao.getAllUserName(conn);

            String content= "fail";
            for(User user: userList){

                if(user.getUserName().equals(username)){
                    content = "fail";
                    break;
                }
                else {
                    content = "pass";
                }
            }

            JSONResponse.send(resp,content);

        } catch (
                SQLException e) {
            throw new ServletException(e);
        }
    }
}

