package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dao.ChatDao;
import ru.itis.dao.UsersDao;
import ru.itis.models.Chat;
import ru.itis.service.ChatService;

import java.util.List;

@RestController
public class ChatController {


    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/chats", method = RequestMethod.POST)
    public void addChat(@RequestBody String chatName, @RequestHeader String token) {
        chatService.createNewChat(chatName, token);
    }

    @RequestMapping(value ="/chats", method = RequestMethod.GET)
    @ResponseBody
    public List<Chat> showAllChats() {
        List<Chat> chats = chatService.showAllChats();
        return chats;
    }

    @RequestMapping(value = "/chat/{chatId}/member", method = RequestMethod.POST)
    @ResponseBody
    public void hz(@PathVariable("chatId") int chatId) {

    }


}
