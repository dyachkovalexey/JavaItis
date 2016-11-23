package ru.itis.models;

public class MessageDto {
    private String from;
    private String text;

    public MessageDto() {
    }

    public MessageDto(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }
}