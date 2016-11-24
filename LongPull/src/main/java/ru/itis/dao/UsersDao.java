package ru.itis.dao;



import ru.itis.models.Users;

import java.util.List;

public interface UsersDao {

    List<Users> findAll();
    Users find();
    int save(Users usersDto);
    void update(Users usersDto);
}
