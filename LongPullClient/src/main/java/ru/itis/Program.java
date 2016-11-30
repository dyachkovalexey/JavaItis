package ru.itis;

import ru.itis.rest.UserRestClient;
import ru.itis.service.ClientMessagesService;
import ru.itis.service.UserService;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        boolean chek = false;
        String request;
        Scanner scanner = new Scanner(System.in);

        //ClientMessagesService service = new ClientMessagesService();
        UserService userService = new UserService();
        //service.messagesProcessing();



        do {
            System.out.println("reg - registration");
            System.out.println("login - authorization");
            System.out.println("list - chat list");
            System.out.println("add - chat add");
            System.out.println("enter - enter to chat");
            System.out.println("exit - finish");
            request = scanner.nextLine();
            switch(request) {
                case "reg":
                    userService.registration();
                    break;
                case "login":
                    break;
                case "list":
                    break;
                case "add":
                    break;
                case "enter":
                    break;
                case "exit":
                    chek = true;
                    break;
            }
        } while (chek == false);
    }
}
