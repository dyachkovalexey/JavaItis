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

@RestController
public class ChatController {

    @Autowired
    private ChatDao chatDao;
    @Autowired
    private UsersDao usersDao;

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    public ModelAndView addChat(@RequestParam("chatId") int chatId, @RequestParam("chatName") String chatName) {
        ModelAndView modelAndView = new ModelAndView();
        Chat chat = chatDao.find(chatId);
        modelAndView.setViewName("chat");
        return modelAndView;
    }
}
