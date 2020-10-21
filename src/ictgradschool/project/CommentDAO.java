package ictgradschool.project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public static List<Comment> getAllComments (Connection conn) throws SQLException {
        List<Comment> comments = new ArrayList<>();

        try (Statement stmt = conn.createStatement()) {

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM pb_comments")) {

                while (rs.next()) {
                    Comment comment = new Comment(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getInt(5),rs.getInt(4));
                    comments.add(comment);
                }
            }
        }
        return comments;
    }

    public static boolean createComment(Connection conn, Comment comment) throws SQLException {

        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO pb_comments (content,userId, articleId) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, comment.getComment());
            stmt.setInt(2, comment.getUserId());
            stmt.setInt(3, comment.getArticleId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                return false;
            }

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                keys.next();
                int commentID = keys.getInt(1);
                comment.setCommentId(commentID);

                return true;
            }

        }
    }

}
