package servlets;

import dao.UserDao;
import factorys.DaoFactory;
import models.Users;
import sun.security.acl.OwnerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao;


    @Override
    public void init() throws ServletException {
        super.init();
        userDao = DaoFactory.getInstance().getOwnersDao();
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
