package ictgradschool.project;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {

    private Integer userId;
    private String userName;
    private String fName;
    private String lName;
    private String passwordHashBase64;
    private String saltBase64;

    private Date dob;
    private String description;
    private String imagePath;

    public User(Integer userId, String userName, String fName, String lName, String passwordHashBase64, String saltBase64, Date dob, String description, String imagePath) {
        this.userId = userId;
        this.userName = userName;
        this.fName = fName;
        this.lName = lName;
        this.passwordHashBase64 = passwordHashBase64;
        this.saltBase64 = saltBase64;
        this.dob = dob;
        this.description = description;
        this.imagePath = imagePath;
    }

    public User(String userName, String fName, String lName, String passwordHashBase64, String saltBase64, Date dob, String description, String imagePath) {
        this.userName = userName;
        this.fName = fName;
        this.lName = lName;
        this.passwordHashBase64 = passwordHashBase64;
        this.saltBase64 = saltBase64;
        this.dob = dob;
        this.description = description;
        this.imagePath = imagePath;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPasswordHashBase64() {
        return passwordHashBase64;
    }

    public void setPasswordHashBase64(String passwordHashBase64) {
        this.passwordHashBase64 = passwordHashBase64;
    }

    public String getSaltBase64() {
        return saltBase64;
    }

    public void setSaltBase64(String saltBase64) {
        this.saltBase64 = saltBase64;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
