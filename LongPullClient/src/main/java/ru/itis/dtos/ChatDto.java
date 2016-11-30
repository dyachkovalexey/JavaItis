package ru.itis.dtos;


public class ChatDto {
    Integer chatId;
    String chatName;


    public ChatDto() {
    }

    public ChatDto(Integer chatId, String chatName) {
        this.chatId = chatId;
        this.chatName = chatName;
    }

    public String getChatName() {
        return chatName;
    }

    public Integer getChatId() { return chatId; }
}
