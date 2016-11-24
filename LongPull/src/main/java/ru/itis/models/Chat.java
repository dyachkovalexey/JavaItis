package ru.itis.models;


public class Chat {

    int chatId;
    String chatName;

    public Chat() {
    }

    public Chat(int chatId, String chatName) {
        this.chatId = chatId;
        this.chatName = chatName;
    }

    public int getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }
}
