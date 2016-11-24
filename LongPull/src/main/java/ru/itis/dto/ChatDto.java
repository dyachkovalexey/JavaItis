package ru.itis.dto;

public class ChatDto {
    String chatName;


    public ChatDto() {
    }

    public ChatDto(String chatName) {
        this.chatName = chatName;
    }

    public String getChatName() {
        return chatName;
    }
}
