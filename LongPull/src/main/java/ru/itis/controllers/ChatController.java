package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ModelAndView addChat(@RequestParam("chatId") int chatId, @RequestParam("chatName") String chatName) {
        ModelAndView modelAndView = new ModelAndView();
        Chat chat = chatDao.find(chatId);
        modelAndView.setViewName("chat/");
        return modelAndView;
    }

    @RequestMapping(value ="/chat", method = RequestMethod.GET)
    public ModelAndView showAllChats() {
        synchronized (service.getListChats()) {
            while (service.getListChats().isEmpty()) {

            }
        }
        ModelAndView modelAndView = new ModelAndView();
        List<Chat> chatList = chatDao.findAll();
        modelAndView.addObject("chatList", chatList);
        modelAndView.setViewName("chat");
        return  modelAndView;
    }

    @RequestMapping(value = "/chat/", method = RequestMethod.GET)
    public ModelAndView showChat() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }

    @RequestMapping(value = "/chat/", method = RequestMethod.POST)
    public ModelAndView addUsers() {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
