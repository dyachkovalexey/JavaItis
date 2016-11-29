package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dao.ChatDao;
import ru.itis.dto.ChatDto;
import ru.itis.models.Chat;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatDao chatDao;

    public ChatService() {
    }

    public void createNewChat(String chatName, String token) {
        Integer userId = chatDao.findByToken(token);
        Chat chat = new Chat(chatName, userId);
        chatDao.save(chat);
    }

    public List<Chat> showAllChats() {
        List<Chat> chatList = chatDao.findAll();
        return  chatList;
    }


}
