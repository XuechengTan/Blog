package ictgradschool.project;

import ictgradschool.project.util.DBConnectionUtils;
import ictgradschool.project.util.PasswordUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EditProfileServlet", urlPatterns = {"/editProfile"})
public class EditProfileDetailServlet extends HttpServlet {

    private File uploadsFolder; // The folder where article images should be uploaded
    private File tempFolder; // The temp folder required by the file-upload logic

    @Override
    public void init() throws ServletException {
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

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("here");
        User user = (User) req.getSession().getAttribute("loginUser");
        System.out.println(user.getUserName());
        try (Connection conn = DBConnectionUtils.getConnectionFromClasspath("connection.properties")) {

            // Set up file upload mechanism
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(tempFolder);
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the form (works differently since we're expecting a file, amongst other form fields).
                List<FileItem> fileItems = upload.parseRequest(req);
                for (FileItem fi : fileItems) {
                    switch (fi.getFieldName()) {
                        case "userName":
                            if(!fi.getString().isEmpty()) {
                                user.setUserName(fi.getString());
                            }
                            break;

                        case"rePassword":
                            if(!fi.getString().isEmpty()) {
                                String password = fi.getString();

                                byte[] saltGenerated = PasswordUtil.getNextSalt();
                                byte[] hashGenerated = PasswordUtil.insecureHash(password);

                                String salt = PasswordUtil.base64Encode(saltGenerated);
                                user.setSaltBase64(salt);
                                String hash = PasswordUtil.base64Encode(hashGenerated);
                                user.setPasswordHashBase64(hash);
                            }
                            break;

                        case "firstName":
                            if(!fi.getString().isEmpty()) {
                                System.out.println("4"+fi.getString());
                                System.out.println("5"+fi.getName());
                                user.setfName(fi.getString());
                            }
                            break;

                        case "lastName":
                            if(!fi.getString().isEmpty()) {
                                System.out.println("6"+fi.getString());
                                user.setlName(fi.getString());
                            }
                            break;

                        case "dateOfBirth":
                            if(!fi.getString().isEmpty()) {
                                System.out.println("7"+fi.getString());
                                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(fi.getString());
                                user.setDob(dob);
                            }
                            break;

                        case "description":
                            if(!fi.getString().isEmpty()) {
                                System.out.println("8"+fi.getString());
                                user.setDescription(fi.getString());
                            }
                            break;

                        case "avatar":
                            if(fi.getString()!=null) {
                                System.out.println("ava = "+fi.getName());
                                user.setImagePath(fi.getString());
                                continue;
                            }
                            break;

                        case "custom-avatar":
                            if(fi.getName()!="") {
                                System.out.println("9"+fi.getString());
                                File imageFile2 = new File(this.uploadsFolder, fi.getName());
                                user.setImagePath(fi.getName());
                                fi.write(imageFile2);

                            }
                            break;
                    }
                }

                    boolean edit = UserDao.editUser(user, conn);

                    if (edit) {
                        System.out.println("Profile edited");
                        req.getSession().setAttribute("LoginUser", user);
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Profile.jsp");
                        dispatcher.forward(req, resp);
                    } else {
                        System.out.println("Profile edit failed");
                    }

            }catch (FileUploadException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            resp.setStatus(500);
            e.printStackTrace();
            throw new ServletException("Database access error!", e);
        }
    }
}
