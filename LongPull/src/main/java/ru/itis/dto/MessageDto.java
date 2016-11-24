package ru.itis.dto;

public class MessageDto {
    private String text;
    private int chatId;



    public MessageDto() {

    }

    public MessageDto(String text, int chatId) {
        this.text = text;
        this.chatId = chatId;
    }


    public String getText() {
        return text;
    }


    public int getChatId() { return chatId; }
}