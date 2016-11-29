package ru.itis.models;


public class Messages {

    int messageId;
    String messageText;
    int userId;
    int chatId;

    public Messages() {
    }

    public Messages(String messageText, int userId, int chatId) {
        this.messageText = messageText;
        this.userId = userId;
        this.chatId = chatId;
    }

    public Messages(int messageId, String messageText, int userId, int chatId) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.userId = userId;
        this.chatId = chatId;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public int getUserId() {
        return userId;
    }

    public int getChatId() {
        return chatId;
    }
}
