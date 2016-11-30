package ru.itis.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import ru.itis.dtos.MessageDto;


public class MessageRestClient {

    private RestTemplate restTemplate;


    public MessageRestClient() {
        restTemplate = new RestTemplate();
    }

    public void sendMessage(MessageDto messageDto) {
        restTemplate.postForObject("http://localhost:8081/messages",
                messageDto, MessageDto.class);
    }



    public MessageDto[] getMessages() {
        // получили ответ на запрос к серверу
        ResponseEntity<MessageDto[]> response = restTemplate.getForEntity(
                "http://localhost:8081/messages", MessageDto[].class);
        // вытащали тело ответа
        MessageDto[] messages = response.getBody();

        return messages;
    }

}
