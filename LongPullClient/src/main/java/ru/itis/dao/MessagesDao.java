package ru.itis.dao;

import ru.itis.models.MessageDto;

import java.util.List;

public interface MessagesDao {
    void add(MessageDto messageDtoo);
    List<MessageDto> select(int currentMessageId);
}
