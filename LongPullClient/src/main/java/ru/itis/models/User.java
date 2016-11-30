package ru.itis.models;

public class User {
    String userName;
    String login;
    Integer hashPassword;

    public User(String userName, String login, Integer hashPassword) {
        this.userName = userName;
        this.login = login;
        this.hashPassword = hashPassword;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(Integer hashPassword) {
        this.hashPassword = hashPassword;
    }
}
