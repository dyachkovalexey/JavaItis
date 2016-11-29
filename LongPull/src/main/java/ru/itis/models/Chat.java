package ru.itis.models;


public class Chat {

    int chatId;
    String chatName;
    int userId;

    public Chat() {
    }

    public Chat(int chatId, String chatName) {
        this.chatId = chatId;
        this.chatName = chatName;
    }

    public Chat(String chatName, Integer userId) {
        this.chatName = chatName;
        this.userId = userId;
    }

    public int getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public int getUserId() { return userId; }
}
