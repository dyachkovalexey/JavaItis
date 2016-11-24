package ru.itis.models;


public class Users {
    int userId;
    String userName;
    String userLogin;
    int userPassword;

    public Users() {
    }

    public Users(int userId, String userName, String userLogin, int userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public int getUserPassword() {
        return userPassword;
    }
}
