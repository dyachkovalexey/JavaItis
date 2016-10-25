package servlets;

import dao.AutoDao;
import dao.UserDao;
import factorys.DaoFactory;
import models.Autos;
import models.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class ListServlet extends HttpServlet{

    private UserDao userDao;
    private AutoDao autoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        userDao = DaoFactory.getInstance().getOwnersDao();
        autoDao = DaoFactory.getInstance().getAutoDao();
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
