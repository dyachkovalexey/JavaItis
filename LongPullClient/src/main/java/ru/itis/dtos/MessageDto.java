package ru.itis.dtos;

public class MessageDto {
    private Integer messageId;
    private Integer chatId;
    private String text;

    public MessageDto() {
    }

    public MessageDto(String text) {
        this.text = text;
    }

    public MessageDto(Integer messageId, String text, Integer chatId) {
        this.messageId = messageId;
        this.text = text;
        this.chatId = chatId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}