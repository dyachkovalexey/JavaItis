package ru.itis.dao;

import ru.itis.models.Users;

import java.util.List;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public interface UserDao {
    List<Users> getAll();
    String registration(Users users);
    Users find(String login);
    Users findByToken(String token);
    void update(String token, int id);

}
