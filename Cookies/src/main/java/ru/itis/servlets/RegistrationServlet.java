package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.dao.UserDao;
import ru.itis.models.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao;
    private static Logger log = Logger.getLogger(RegistrationServlet.class.getName());


    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        log.info("userDao RegistrationServlet initiation");
    }


    public void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }


    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)  throws ServletException, IOException{
            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");

                String userName =  request.getParameter("userName");
                String login = request.getParameter("userLogin");
                String password = request.getParameter("password");

                Users users = new Users(userName, login, password.hashCode());

                userDao.registration(users);
                getServletContext().getRequestDispatcher("/jsp/login.jsp").forward(request, response);

    }
}
