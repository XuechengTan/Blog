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
import java.sql.SQLException;

@WebServlet (name = "EditPasswordServlet", urlPatterns = {"/editPassword"})
public class EditPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("loginUser");

        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("password");
        String confirmPassword = req.getParameter("rePassword");

        byte[] hashGenerated = PasswordUtil.insecureHash(oldPassword);
        String oldHash = PasswordUtil.base64Encode(hashGenerated);

        //check old password match current password
        if (oldHash.equals(user.getPasswordHashBase64())) {
            System.out.println("Old password match new password");
            //check if the new password is confirmed
            if(newPassword.equals(confirmPassword)) {
                System.out.println("updating to new password");
                byte[] saltGenerated = PasswordUtil.getNextSalt();
                byte[] newHashGenerated = PasswordUtil.insecureHash(confirmPassword);

                String salt = PasswordUtil.base64Encode(saltGenerated);
                user.setSaltBase64(salt);
                String hash = PasswordUtil.base64Encode(newHashGenerated);
                user.setPasswordHashBase64(hash);
            }else{

            }

            boolean edit = UserDao.editPassword(user, conn);

            if (edit) {
                System.out.println("Profile edited");
                req.getSession().setAttribute("LoginUser", user);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Profile.jsp");
                dispatcher.forward(req, resp);
            } else {
                System.out.println("Profile edit failed");
            }

        }else{
            resp.sendRedirect("");
        }

    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
