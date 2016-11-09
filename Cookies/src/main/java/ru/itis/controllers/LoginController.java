package ru.itis.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.dao.UserDao;
import ru.itis.models.Users;
import ru.itis.servlets.LoginServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.logging.Logger;

/**
 * Created by KFU-user on 09.11.2016.
 */
public class LoginController implements Controller {

    private UserDao userDao;
    private SecureRandom random = new SecureRandom();
    private  static Logger logger = Logger.getLogger(LoginServlet.class.getName());

    public LoginController() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        logger.info("userDao LoginServlet initiation");
    }

    public String nextSessionId() {
        return new BigInteger(100, random).toString(32);
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        if (request.getMethod().equals("GET")) {
            modelAndView.setViewName("login");
        }
        if (request.getMethod().equals("POST")) {
            String login = request.getParameter("userLogin");
            int password = request.getParameter("password").hashCode();
            String token = null;

            Users users = userDao.find(login);

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
        }
        return modelAndView;
    }
}
