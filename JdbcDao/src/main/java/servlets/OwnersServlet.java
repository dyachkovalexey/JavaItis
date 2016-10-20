package servlets;

import dao.OwnersDao;
import factorys.DaoSupportFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Lo0ny on 19.10.2016.
 */
public class OwnersServlet extends HttpServlet{

    private OwnersDao ownersDao;

    @Override
    public  void init() throws ServletException {
        super.init();
        ownersDao = DaoSupportFactory.getInstance().getOwnersDao();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");

        List<String> result = ownersDao.getAll();
        try {
            PrintWriter out = null;
            out = response.getWriter();
            out.println("<h1>Список пользователей:</h1>");
            for (String owners: result)
                out.println("<p>" + owners + "</p>");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
