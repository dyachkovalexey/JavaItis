package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import ru.itis.dao.MessagesDao;
import ru.itis.models.MessageDto;

import java.util.List;

@Controller
public class MyRestClient {

    private RestTemplate restTemplate;
    public int count;

    @Autowired
    private MessagesDao messagesDao;

    public MyRestClient() {
        restTemplate = new RestTemplate();
        count = 0;
    }

    public void sendMessage(MessageDto messageDto) {
        restTemplate.postForObject("http://localhost:8081/messages",
                messageDto, MessageDto.class);
        //занесение текста в БД
        messagesDao.add(messageDto); /** messagesDao = null*/
        count++;
    }

    public List<MessageDto>/*MessageDto[]*/ getMessages() {
        //TODO: get text from DB
        List<MessageDto> message = messagesDao.select(count);
        /**
        // получили ответ на запрос к серверу
        ResponseEntity<MessageDto[]> response = restTemplate.getForEntity(
                "http://localhost:8081/messages", MessageDto[].class);

        // вытащали тело ответа
        MessageDto[] messages = response.getBody();
         */

        return message;
    }

}
