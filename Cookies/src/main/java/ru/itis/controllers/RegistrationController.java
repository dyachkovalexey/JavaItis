package ru.itis.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.dao.UserDao;
import ru.itis.models.Users;
import ru.itis.servlets.RegistrationServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class RegistrationController implements Controller{

    private UserDao userDao;
    private static Logger log = Logger.getLogger(RegistrationServlet.class.getName());

    public RegistrationController() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        log.info("userDao RegistrationServlet initiation");
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (request.getMethod().equals("GET")) {
            modelAndView.setViewName("registration");
        }
        if (request.getMethod().equals("POST")) {
            String userName = request.getParameter("userName");
            String login = request.getParameter("userLogin");
            String password = request.getParameter("password");

            Users users = new Users(userName, login, password.hashCode());
            userDao.registration(users);
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }
}
