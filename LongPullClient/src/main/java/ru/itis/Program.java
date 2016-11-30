package ru.itis;

import ru.itis.rest.UserRestClient;
import ru.itis.service.ClientMessagesService;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        ClientMessagesService service = new ClientMessagesService();
        UserRestClient userRestClient = new UserRestClient();
        //service.messagesProcessing();

        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        switch(request) {
            case "/users": userRestClient.registration(); break;
            case "/login": break;
            case "/chats": break;
            case "/chat/{chatId}/member": break;
            case "/chat/{chatId}/messages": break;
            case "/chat/{chatId}/messages?get=all": break;
            case "/chats/{chatId}/messages": break;
            default: request = scanner.nextLine(); break;
        }
    }
}
