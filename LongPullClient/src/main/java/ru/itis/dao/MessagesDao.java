package ru.itis.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.MessageDto;

import java.util.List;

@Repository
@Service
@Transactional
public interface MessagesDao {
    void add(MessageDto messageDtoo);
    List<MessageDto> select(int currentMessageId);
}
