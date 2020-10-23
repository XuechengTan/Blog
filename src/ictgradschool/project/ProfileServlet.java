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
import java.sql.SQLOutput;

@WebServlet (name = "profile",  urlPatterns = {"/profile"} )
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
//            System.out.println("here I am");
//
//            User userSession =(User)req.getSession().getAttribute("loginUser");
//            int userId = userSession.getUserId();
//
//            User user = UserDao.getUserByUserId(userId,conn);
//            req.setAttribute("user", user);
//
//            System.out.println("userid "+ user.getUserId());
//            System.out.println("username " + user.getUserName());
//            System.out.println("firstname " + user.getfName());
//            System.out.println("lastname "+ user.getlName());
//            System.out.println("dob" + user.getDob());
//            System.out.println("description "+ user.getDescription());
//            System.out.println("filepath"+ user.getImagePath());
//
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Profile.jsp");
//            dispatcher.forward(req, resp);
//
//        } catch (SQLException e) {
//            throw new ServletException(e);
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
