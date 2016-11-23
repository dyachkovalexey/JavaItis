package ru.itis;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessagesService {

    private List<MessageDto> newMessages;

    public MessagesService() {
        newMessages = new ArrayList<>();
    }

    public void handleMessage(MessageDto inputMessage) {
        synchronized (newMessages) {
            newMessages.add(inputMessage);
            newMessages.notify();
        }
    }

    public List<MessageDto> getNewMessages() {
        return newMessages;
    }
}
