package ru.itis.dao;



import ru.itis.models.Users;

import java.util.List;

public interface UsersDao {

    List<Users> findAll();
    Users find(Integer id);
    void save(Users users);
    void update(Users user, String token);
    void saveUserToChat(Integer userId, Integer chatId);
    void addToken(Users user, String token);
    Users findByLogin(String login);
}
