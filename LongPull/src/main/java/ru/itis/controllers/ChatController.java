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
    private ChatDao chatDao;
    @Autowired
    private UsersDao usersDao;

    @Autowired
    private ChatService service;

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addChat(@RequestParam("chatId") int chatId, @RequestParam("chatName") String chatName) {
        ModelAndView modelAndView = new ModelAndView();
        Chat chat = chatDao.find(chatId);
        int currentChatId = chat.getChatId();
        chatDao.save(chat);
        modelAndView.setViewName("chat/{currentChatId}");
        return modelAndView;
    }

    @RequestMapping(value ="/chat", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showAllChats() {
        ModelAndView modelAndView = new ModelAndView();
        List<Chat> chatList = chatDao.findAll();
        modelAndView.addObject("chatList", chatList);
        modelAndView.setViewName("chat");
        return  modelAndView;
    }

    @RequestMapping(value = "/chat/{chatId}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showChat(@PathVariable("chatId") int chatId) {
        ModelAndView modelAndView = new ModelAndView();
        Chat chat = chatDao.find(chatId);
        return modelAndView;
    }

    @RequestMapping(value = "/chat/{chatId}", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUsers(@PathVariable("chatId") int chatId) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
