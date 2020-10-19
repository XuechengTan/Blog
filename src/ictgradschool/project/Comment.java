package ictgradschool.project;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
    Integer commentId;
    String comment;
    Date date;
    Integer articleId;
    Integer userId;

    public Comment(Integer commentId, String comment, Date date, Integer articleId, Integer userId) {
        this.commentId = commentId;
        this.comment = comment;
        this.date = date;
        this.articleId = articleId;
        this.userId = userId;
    }

    public Comment(String comment, Date date, Integer articleId, Integer userId) {
        this.comment = comment;
        this.date = date;
        this.articleId = articleId;
        this.userId = userId;
    }

    public Comment() {
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
