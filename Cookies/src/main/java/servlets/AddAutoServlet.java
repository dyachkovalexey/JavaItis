package servlets;

import dao.AutoDao;
import dao.UserDao;
import factorys.DaoFactory;
import models.Autos;
import models.Users;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class AddAutoServlet extends HttpServlet {

    private AutoDao autoDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        autoDao = DaoFactory.getInstance().getAutoDao();
        userDao = DaoFactory.getInstance().getOwnersDao();
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
    }
}
