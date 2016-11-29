package ru.itis.dao;


import ru.itis.dto.MessageDto;
import ru.itis.models.Messages;
import ru.itis.models.Users;

import java.util.List;

public interface MessagesDao {

    List<Messages> findAll();
    Messages find(Integer messageId);
    List<MessageDto> findAllByChatId(Integer chatId);
    void save(MessageDto messageDto, Integer userId);
    void update(Messages messages);
}
