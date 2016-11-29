package ru.itis.converters;


import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.dto.ChatDto;
import ru.itis.models.Chat;

public class ChatsConverter {

    @Autowired
    public ChatsConverter() {
    }

    public ChatDto ConvertChatsToChatDto(Chat chat) {
        ChatDto chatDto = new ChatDto(chat.getChatId(), chat.getChatName());
        return chatDto;
    }
}
