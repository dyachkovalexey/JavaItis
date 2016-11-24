package ru.itis.dao;


import ru.itis.models.Messages;

import java.util.List;

public interface MessagesDao {

    List<Messages> findAll();
    Messages find();
    int save(Messages messages);
    void update(Messages messages);
}
