package ru.itis.service;

import org.springframework.stereotype.Service;
import ru.itis.dto.ChatDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private List<ChatDto> listChats;

    public ChatService() {
        listChats = new ArrayList<>();
    }

    public void handleChats(ChatDto chatDto) {
        synchronized (listChats) {
            listChats.add(chatDto);
            listChats.notify();
        }
    }

    public List<ChatDto> getListChats() {
        return listChats;
    }
}
