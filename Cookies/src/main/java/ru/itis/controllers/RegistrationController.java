package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dao.UserDao;
import ru.itis.models.Users;

@Controller
public class RegistrationController {


    private UserDao userDao;

    @RequestMapping(value = "/registration")
    public ModelAndView ShowPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView RegistrationNewUser(@RequestParam("userName") String userName,
                                            @RequestParam("userLogin") String login,
                                            @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        Users user = null;
        try {
            user = userDao.find(login);
        } catch (NullPointerException e) {}
        if (user!=null) {
            Users users = new Users(userName, login, password.hashCode());
            userDao.registration(users);
            modelAndView.setViewName("login");
        } else { modelAndView.setViewName("registration"); }
        return modelAndView;
    }
}
