package ru.itis.dao;

import ru.itis.models.Chat;

import java.util.List;

/**
 * Created by KFU-user on 24.11.2016.
 */
public interface ChatDao {

    List<Chat> findAll();
    Chat find(int chatId);
    int save(Chat chat);
    void update(Chat chat);
}
