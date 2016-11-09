package ru.itis.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.dao.AutoDao;
import ru.itis.dao.UserDao;
import ru.itis.models.Autos;
import ru.itis.models.Users;
import ru.itis.servlets.ListServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by KFU-user on 09.11.2016.
 */
public class ListController implements Controller {

    private UserDao userDao;
    private AutoDao autoDao;
    private static Logger logger = Logger.getLogger(ListServlet.class.getName());

    public ListController() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        logger.info("userDao ListServlet initiation");
        this.autoDao = (AutoDao)applicationContext.getBean("autoDao");
        logger.info("autoDao ListServlet initiation");
    }
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        if (httpServletRequest.getMethod().equals("GET")) {
            List<Users> users = userDao.getAll();
            List<Autos> autos = autoDao.getAll();

            modelAndView.addObject("usersList", users);
            modelAndView.addObject("autosList", autos);
            modelAndView.setViewName("list");
        }
        if (httpServletRequest.getMethod().equals("POST")) {
            modelAndView.setViewName("addAuto");
        }
        return modelAndView;
    }
}
