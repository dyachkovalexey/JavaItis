package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dao.AutoDao;
import ru.itis.dao.UserDao;
import ru.itis.models.Autos;
import ru.itis.models.Users;

import java.util.List;

@Controller
public class ListController{

    @Autowired
    private UserDao userDao;
    @Autowired
    private AutoDao autoDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView ShowUsers() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Users> users = userDao.getAll();
        List<Autos> autos = autoDao.getAll();

            modelAndView.addObject("usersList", users);
            modelAndView.addObject("autosList", autos);
            modelAndView.setViewName("list");
        return modelAndView;
    }
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ModelAndView ShowAddAutoPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addAuto");
        return modelAndView;
    }
}
