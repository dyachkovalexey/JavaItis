package ru.itis.dao;

import ru.itis.models.Users;

import java.util.List;

public interface UserDao {
    List<Users> getAll();
    void registration(Users users);
    Users find(String login);
    Users findByToken(String token);
    void update(String token, int id);

}
