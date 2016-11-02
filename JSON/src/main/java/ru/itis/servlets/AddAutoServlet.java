package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.dao.AutoDao;
import ru.itis.dao.UserDao;
import ru.itis.models.Autos;
import ru.itis.models.Users;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class AddAutoServlet extends HttpServlet {

    private AutoDao autoDao;
    private UserDao userDao;
    private static Logger logger = Logger.getLogger(AddAutoServlet.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        logger.info("userDao AddAutoServlet initiation");
        this.autoDao = (AutoDao)applicationContext.getBean("autoDao");
        logger.info("autoDao AddAutoServlet initiation");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        getServletContext().getRequestDispatcher("/jsp/addAuto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = 0;
        Cookie cookie[] = request.getCookies();
        for (int i = cookie.length-1; i > 0; i--) {
            Users users = userDao.findByToken(cookie[i].getValue());
            if (users != null)
            if (cookie[i].getValue().equals(users.getUserToken())) {
                id = users.getUserId();
                break;
            }
        }

        String autoName = request.getParameter("autoName");
        String number = request.getParameter("autoNumber");
        Autos autos = new Autos(autoName, number, id);

        autoDao.add(autos);
        response.sendRedirect("/list");
    }
}
