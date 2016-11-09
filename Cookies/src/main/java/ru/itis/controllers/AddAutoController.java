package ru.itis.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.itis.dao.AutoDao;
import ru.itis.dao.UserDao;
import ru.itis.models.Autos;
import ru.itis.models.Users;
import ru.itis.servlets.AddAutoServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class AddAutoController implements Controller {

    private AutoDao autoDao;
    private UserDao userDao;
    private static Logger logger = Logger.getLogger(AddAutoServlet.class.getName());

    public AddAutoController() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        logger.info("userDao AddAutoServlet initiation");
        this.autoDao = (AutoDao)applicationContext.getBean("autoDao");
        logger.info("autoDao AddAutoServlet initiation");
    }
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        if (httpServletRequest.getMethod().equals("GET")) {
            modelAndView.setViewName("addAuto");
        }
        if (httpServletRequest.getMethod().equals("POST")) {
            int id = 0;
            Cookie cookie[] = httpServletRequest.getCookies();
            for (int i = cookie.length - 1; i > 0; i--) {
                Users users = userDao.findByToken(cookie[i].getValue());
                if (users != null)
                    if (cookie[i].getValue().equals(users.getUserToken())) {
                        id = users.getUserId();
                        break;
                    }
            }

            String autoName = httpServletRequest.getParameter("autoName");
            String number = httpServletRequest.getParameter("autoNumber");
            Autos autos = new Autos(autoName, number, id);

            autoDao.add(autos);

            modelAndView.setViewName("list");
        }
        return modelAndView;
    }
}
