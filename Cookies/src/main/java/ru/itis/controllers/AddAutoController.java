package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dao.AutoDao;
import ru.itis.dao.UserDao;
import ru.itis.models.Autos;
import ru.itis.models.Users;

@Controller
public class AddAutoController{


    @Autowired
    private AutoDao autoDao;
    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "/addAuto", method = RequestMethod.GET)
    public ModelAndView ShowFields() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addAuto");
        return modelAndView;
    }

    @RequestMapping(value = "/addAuto", method = RequestMethod.POST)
    public ModelAndView AddAuto(@RequestParam("autoName") String autoName,
                                      @RequestParam("autoNumber") String number,
                                      @CookieValue(value = "token", defaultValue = "0") String token) {
        ModelAndView modelAndView = new ModelAndView();
        Users users = userDao.findByToken(token);
        int id = users.getUserId();
        System.out.println(id);
        Autos autos = new Autos(autoName, number, id);
        autoDao.add(autos);
        modelAndView.setViewName("list");
        return modelAndView;
    }
}
