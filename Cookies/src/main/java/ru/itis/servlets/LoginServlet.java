package ru.itis.servlets;

import ru.itis.dao.UserDao;
import ru.itis.models.Users;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.logging.Logger;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class LoginServlet extends HttpServlet{



    private UserDao userDao;
    private SecureRandom random = new SecureRandom();
    private  static Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        logger.info("userDao LoginServlet initiation");
    }

    public String nextSessionId() {
        return new BigInteger(100, random).toString(32);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String token = null;

        String login = request.getParameter("userLogin");
        int password = request.getParameter("password").hashCode();

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
            response.sendRedirect("/login");
        } else {
            response.sendRedirect("/list");
        }
    }
}
