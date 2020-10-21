package ictgradschool.project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public static List<User> getAllUsernameAndID(Connection conn) throws SQLException {
        List<User> users = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {

            try (ResultSet rs = stmt.executeQuery("SELECT userId, username FROM pb_user")) {

                while (rs.next()) {
                    User user = new User(rs.getInt(1),rs.getString(2));
                    users.add(user);
                }
            }
        }
        return users;
    }

    public static List<User> getAllUserName(Connection conn) throws SQLException {

        List<User> users = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {

            try (ResultSet rs = stmt.executeQuery("SELECT username FROM pb_user")) {

                while (rs.next()) {
                    User user = new User(rs.getString(1));
                    users.add(user);
                }
            }
        }
        return users;
    }

    public static boolean insertUser(User user, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO pb_user (username, fname, lname, dob, passHashBase64, saltBase64, description, imageFilename) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getfName());
            stmt.setString(3, user.getlName());
            stmt.setDate(4, new java.sql.Date(user.getDob().getTime()));
            stmt.setString(5, user.getPasswordHashBase64());
            stmt.setString(6,user.getSaltBase64());
            stmt.setString(7,user.getDescription());
            stmt.setString(8,user.getImagePath());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                keys.next(); // Move to the fist row.
                int id = keys.getInt(1);
                user.setUserId(id);

                return true;
            }
        }
    }

}
