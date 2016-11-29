package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dao.ChatDao;
import ru.itis.dao.MessagesDao;
import ru.itis.dto.MessageDto;
import ru.itis.models.Users;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    @Autowired
    private MessagesDao messagesDao;
    @Autowired
    private ChatDao chatDao;

    private List<MessageDto> newMessages;

    public MessagesService() {
        newMessages = new ArrayList<>();
    }

    public void handleMessage(MessageDto inputMessage, String token) {
        synchronized (newMessages) {
            Integer userId = chatDao.findByToken(token);
            messagesDao.save(inputMessage, userId);
            newMessages.add(inputMessage);
            newMessages.notify();
        }
    }

    public List<MessageDto> getNewMessages() {
        return newMessages;
    }

    public List<MessageDto> getAllMessages(Integer chatId) {
        List<MessageDto> messageDtoList = messagesDao.findAllByChatId(chatId);
        return messageDtoList;
    }
}
