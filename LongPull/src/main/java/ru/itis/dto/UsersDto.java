package ru.itis.dto;

public class UsersDto {
    String userName;

    public UsersDto() {
    }

    public UsersDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
