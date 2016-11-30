package ru.itis.service;

import ru.itis.models.User;
import ru.itis.rest.UserRestClient;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserService {

    private UserRestClient userRestClient;
    private ExecutorService executorService;
    Scanner scanner = new Scanner(System.in);

    public UserService() {
        executorService = Executors.newFixedThreadPool(1);
        userRestClient = new UserRestClient();
    }

    public void registration() {
        System.out.println("enter your name");
        String userName = scanner.nextLine();
        System.out.println("enter you login");
        String login = scanner.nextLine();
        System.out.println("enter you password");
        String password = scanner.nextLine();
        userRestClient.registration(userName, login, password.hashCode());
    }
}
