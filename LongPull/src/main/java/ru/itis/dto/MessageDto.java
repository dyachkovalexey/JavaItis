package ru.itis.dto;

public class MessageDto {
    private String text;
    private Integer chatId;
    private Integer messageId;

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

    public void setChatId(Integer chatId) {
        this.chatId = chatId;
    }

    public Integer getChatId() {
        return chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}