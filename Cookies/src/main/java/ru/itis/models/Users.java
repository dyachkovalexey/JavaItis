package ru.itis.models;


import com.google.common.base.MoreObjects;

public class Users {
    private int userId;
    private String userName;
    private String userLogin;
    private int userPassword;
    private String userToken;

    public Users(int userId, String userName, String userLogin, int userPassword, String userToken) {
        this.userId = userId;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.userToken = userToken;
    }

    public Users(String userName, String userLogin, int userPassword) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserLogin() { return this.userLogin; }

    public int getUserPassword() {
        return this.userPassword;
    }

    public String getUserToken() { return this.userToken; }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("ID", this.getUserId())
                .add("Name", this.getUserName())
                .toString();
    }


}
