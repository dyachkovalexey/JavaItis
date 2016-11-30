package ru.itis.dtos;


public class UserDto {
    String userName;

    public UserDto() {
    }

    public UserDto(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
