package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.PasswordUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "signUp",  urlPatterns = {"/signUp"} )
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("userName");
        String password = req.getParameter("rePassword");

        byte[] saltGenerated = PasswordUtil.getNextSalt();
        byte[] hashGenerated = PasswordUtil.insecureHash(password);

        String salt = PasswordUtil.base64Encode(saltGenerated);
        String hash = PasswordUtil.base64Encode(hashGenerated);

        String fName = req.getParameter("firstName");
        String lName = req.getParameter("lastName");
        Date dob = null;

        try {
            dob = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("dateOfBirth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String description = req.getParameter("description");
        String imageFile = req.getParameter("avatar");

        User newUser = new User(username, fName, lName, hash, salt, dob, description, imageFile);
        req.getSession().setAttribute("newUser", newUser);


        // Save the article to the DB.
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
            UserDao.insertUser(newUser, conn);
        }

        catch (Exception e) {
            throw new ServletException(e);
        }

        // Redirect to the main articles page.
        resp.sendRedirect("./index.jsp");
    }

}
