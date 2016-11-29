package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itis.service.MessagesService;
import ru.itis.dto.MessageDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MessagesController {


    @Autowired
    MessagesService messagesService;

    @RequestMapping(value = "/chat/{chatId}/messages", method = RequestMethod.GET)
    //TODO: refractor
    public List<MessageDto> getMessage(@PathVariable("chatId") Integer chatId) {
        synchronized (messagesService.getNewMessages()) {
            // пока список пустой
            while (messagesService.getNewMessages().isEmpty()) {
                // мы ждем на этом списке
                try {
                    messagesService.getNewMessages().wait();
                } catch (InterruptedException e) {
                    throw new IllegalArgumentException();
                }
            }
            // мы формируем ответ клиенту
            List<MessageDto> result = new ArrayList<>(messagesService.getNewMessages());
            // список сообщений очистили
            messagesService.getNewMessages().clear();
            // отправили ответ
            return result;
        }
    }

    @RequestMapping(value = "/chat/{chatId}/messages?get=all", method = RequestMethod.GET)
    public List<MessageDto> getAllMessages(@PathVariable("chatId") Integer chatId) {
        List<MessageDto> messageDtoList = messagesService.getAllMessages(chatId);
        return messageDtoList;
    }

    @RequestMapping(value = "/chats/{chatId}/messages", method = RequestMethod.POST)
    public void sendMessage(@RequestBody MessageDto messageDto, @PathVariable("chatId") Integer chatId, @RequestHeader String token) {
        messageDto.setChatId(chatId); //bike ^_^
        messagesService.handleMessage(messageDto, token);
    }
}
