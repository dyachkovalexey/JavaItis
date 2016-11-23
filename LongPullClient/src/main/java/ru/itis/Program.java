package ru.itis;

import ru.itis.service.ClientMessagesService;

public class Program {
    public static void main(String[] args) {
        ClientMessagesService service = new ClientMessagesService();
        service.messagesProcessing();
    }
}
