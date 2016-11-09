package ru.itis.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    private AutoDao autoDao;
    private UserDao userDao;

    public AddAutoController() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        this.autoDao = (AutoDao)applicationContext.getBean("autoDao");
    }

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
