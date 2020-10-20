package ictgradschool.project;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    private Integer articleId;
    private String title;
    private String content;
    private Date date;
    private String imageFile;
    private Integer authorID;

    public Article(Integer articleId, String title, String content, Date date, String imageFile, Integer authorID) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.imageFile = imageFile;
        this.authorID = authorID;
    }

    public Article(String title, String content, Date date, String imageFile, Integer authorID) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.imageFile = imageFile;
        this.authorID = authorID;
    }

    public Article(String title, String content, Integer authorID) {
        this.title = title;
        this.content = content;
        this.authorID = authorID;
    }

    public Article() {
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", imageFile='" + imageFile + '\'' +
                ", authorID=" + authorID +
                '}';
    }
}
