package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dao.UserDao;
import ru.itis.models.Users;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;

@Controller
public class LoginController{



    @Autowired
    private UserDao userDao;
    private SecureRandom random = new SecureRandom();


    public String nextSessionId() {
        return new BigInteger(100, random).toString(32);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView ShowPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView LoginIn(@RequestParam("userLogin") String login,
                                      @RequestParam("password") String stringPassword,
                                      @CookieValue(value = "token", defaultValue = "0") String token,
                                      HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();

        Users users = userDao.find(login);
        int password = stringPassword.hashCode();

        if (users != null) {
            if (users.getUserPassword() == password) {
                token = nextSessionId();
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(5 * 60); // пять минут
                response.addCookie(cookie);
                userDao.update(token, users.getUserId());
            }
        }

        if (token == null) {
            modelAndView.setViewName("login");
        } else {
            modelAndView.setViewName("list");
        }
        return modelAndView;
    }
}
