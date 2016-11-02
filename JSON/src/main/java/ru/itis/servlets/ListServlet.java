package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.dao.AutoDao;
import ru.itis.dao.UserDao;
import ru.itis.models.Autos;
import ru.itis.models.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class ListServlet extends HttpServlet{

    private UserDao userDao;
    private AutoDao autoDao;
    private static Logger logger = Logger.getLogger(ListServlet.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        logger.info("userDao ListServlet initiation");
        this.autoDao = (AutoDao)applicationContext.getBean("autoDao");
        logger.info("autoDao ListServlet initiation");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        List<Users> users = userDao.getAll();
        List<Autos> autos = autoDao.getAll();
        request.setAttribute("Users", users);
        request.setAttribute("Autos", autos);

        getServletContext().getRequestDispatcher("/jsp/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/addAuto");
    }
}
