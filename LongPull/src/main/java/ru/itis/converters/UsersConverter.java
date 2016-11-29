package ru.itis.converters;


import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.dto.UsersDto;
import ru.itis.models.Users;

public class UsersConverter {

    @Autowired
    public UsersConverter() {
    }

    public UsersDto ConvertUsersToUserDto(Users users) {
        UsersDto usersDto = new UsersDto(users.getUserName());
        return  usersDto;
    }
}
