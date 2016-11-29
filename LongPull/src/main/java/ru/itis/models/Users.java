package ru.itis.models;


public class Users {
    Integer userId;
    String userName;
    String userLogin;
    int userHashPassword;

    public Users() {
    }

    public Users(Integer userId, String userName, String userLogin, int userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userHashPassword = userPassword;
    }

    public Users(String userName, String userLogin, int userPassword) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userHashPassword = userPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public Integer getUserHashPassword() {
        return userHashPassword;
    }
}
