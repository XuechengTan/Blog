package ictgradschool.project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleAccessDAO {

    public static List<Article> getAllArticles(Connection conn) throws SQLException {

        List<Article> articles = new ArrayList<>();

        try (Statement stmt = conn.createStatement())
        {

            try (ResultSet rs = stmt.executeQuery("SELECT * FROM pb_article")) {
                while(rs.next()) {
                    Article article = createArticleFromResultSet(rs);
                    articles.add(article);
                }
            }

        }
        return articles;
    }

    public static Article createArticleFromResultSet(ResultSet rs) throws SQLException {
        Article article = new Article (
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDate(4),
                rs.getString(5),
                rs.getInt(6)
        );
        return article;
    }

}
