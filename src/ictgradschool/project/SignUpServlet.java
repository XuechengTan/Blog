package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.PasswordUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@WebServlet(name = "signUp",  urlPatterns = {"/signUp"} )
public class SignUpServlet extends HttpServlet {

    private File uploadsFolder; // The folder where article images should be uploaded
    private File tempFolder; // The temp folder required by the file-upload logic

    @Override
    public void init() throws ServletException {
        super.init();

        // Get the upload folder, ensure it exists.
        this.uploadsFolder = new File(getServletContext().getRealPath("/images"));
        if (!uploadsFolder.exists()) {
            uploadsFolder.mkdirs();
        }
        // Create the temporary folder that the file-upload mechanism needs.
        this.tempFolder = new File(getServletContext().getRealPath("/WEB-INF/temp"));
        if (!tempFolder.exists()) {
            tempFolder.mkdirs();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("./index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        // Set up file upload mechanism
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(tempFolder);
        ServletFileUpload upload = new ServletFileUpload(factory);

        User newUser = new User();
            try {
                // Parse the form (works differently since we're expecting a file, amongst other form fields).
                List<FileItem> fileItems = upload.parseRequest(req);
                for (FileItem fi : fileItems) {
                    switch (fi.getFieldName()) {
                        case "userName":
                        newUser.setUserName(fi.getString());
                        break;

                        case"rePassword":
                        String password = fi.getString();

                        byte[] saltGenerated = PasswordUtil.getNextSalt();
                        byte[] hashGenerated = PasswordUtil.insecureHash(password);

                        String salt = PasswordUtil.base64Encode(saltGenerated);
                        newUser.setSaltBase64(salt);
                        String hash = PasswordUtil.base64Encode(hashGenerated);
                        newUser.setPasswordHashBase64(hash);
                        break;

                        case "firstName":
                        newUser.setfName(fi.getString());
                        break;

                        case "lastName":
                        newUser.setlName(fi.getString());
                        break;

                        case "dateOfBirth":
                        Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(fi.getString());
                        newUser.setDob(dob);
                        break;

                        case "description":
                        newUser.setDescription(fi.getString());
                        break;

                        case "avatar":
                            if(fi.getString()!=null) {
                                System.out.println("ava = "+fi.getName());
                                newUser.setImagePath(fi.getString());
                                continue;
                            }
                            break;

                        case "custom-avatar":
                            if(fi.getName()!="") {
                                File imageFile2 = new File(this.uploadsFolder, fi.getName());
                                newUser.setImagePath(fi.getName());
                                fi.write(imageFile2);

                            }
                            break;
                    }
                }

                try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {
                    UserDao.insertUser(newUser, conn);

                }

            } catch (Exception e) {
                throw new ServletException(e);
            }
            req.getSession().setAttribute("user", newUser);
        // Redirect to the main login page.
        resp.sendRedirect("./Login.html");
    }

}



