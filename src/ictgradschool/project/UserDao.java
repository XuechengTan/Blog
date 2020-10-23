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

    public static List<User> getAllUsers(Connection conn) throws SQLException {

        List<User> users = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT * FROM pb_user")) {

                // We can iterate through all rows in the ResultSet like this...
                while (rs.next()) {
                    User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(6),rs.getString(7),rs.getDate(5), rs.getString(8),rs.getString(9) );
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

    public static User getUserByUserId(int userId, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pb_user WHERE a.userId=?")) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return getUserFromResultSet(rs);

                }else{
                    return null;
                }
            }
        }
    }

    private static User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDate(4),
                rs.getString(5),
                rs.getString(6)
        );
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

    public static boolean deleteUser(int userId, Connection conn) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(
                "DELETE FROM pb_user WHERE userId = ?")) {

            stmt.setInt(1, userId);

            int rowsAffected = stmt.executeUpdate();

            return (rowsAffected == 1);

        }

    }

    public static boolean editArticle(User user, Connection conn) throws SQLException {

       // username, fname, lname, dob, passHashBase64, saltBase64, description, imageFilename
        try (PreparedStatement stmt = conn.prepareStatement(
                "UPDATE pb_user SET username = ?, fname = ?, lname = ?, dob = ? description = ?, imageFilename =? WHERE articleId = ?")) {


            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getfName());
            stmt.setString(3, user.getlName());
            stmt.setDate(4, new java.sql.Date(user.getDob().getTime()));
            stmt.setString(5,user.getDescription());
            stmt.setString(6,user.getImagePath());

            int rowsAffected = stmt.executeUpdate();


            return (rowsAffected == 1);

        }

    }


}
