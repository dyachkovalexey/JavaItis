package ru.itis.service;

import ru.itis.rest.MessageRestClient;
import ru.itis.dtos.MessageDto;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientMessagesService {

    // сервис, который запускает задачи, которые необходимо
    // выполнять в потоках
    // реализует шаблон ThreadPool
    private ExecutorService executorService;

    // обертка над RestTemplate - класс, позволяющий отправлять HTTP сообщения
    // на сервер
    private MessageRestClient messageRestClient;

    public ClientMessagesService() {
        // создали пул потоков из одного потока
        executorService = Executors.newFixedThreadPool(1);
        messageRestClient = new MessageRestClient();
    }

    private void messagesHandle() {
        // перехват сообщений с сервера
        Runnable handleMessagesTask = () -> {
            while(true) {
                // мы получаем сообщения
                MessageDto[] messages = messageRestClient.getMessages();
                // выводим каждое сообщение на экран
                for (MessageDto currentMessage : messages) {
                    System.out.println(currentMessage.getText());
                }
            }
        };
        // запускаем задачу на выполнение
        executorService.submit(handleMessagesTask);
    }

    //основной метод приложения
    public void messagesProcessing() {
        // запускаем обработку сообщений сервера
        messagesHandle();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            // считываем текст сообщения
            String text = scanner.nextLine();
            // создаем сообщение
            //TODO: get message
            MessageDto messageDto = new MessageDto(1,text, 1);
            // отправляем на сервер
            messageRestClient.sendMessage(messageDto);
        }
    }
}
