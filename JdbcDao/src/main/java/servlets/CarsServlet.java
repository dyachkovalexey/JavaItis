package servlets;

import dao.CarsDao;
import dao.CarsDaoJdbcImpl;
import factorys.ConnectSupportFactory;
import factorys.DaoSupportFactory;
import models.Cars;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarsServlet extends HttpServlet {

    private CarsDao carsDao;

    @Override
    public  void init() throws ServletException{
        super.init();
        carsDao = DaoSupportFactory.getInstance().getCarsDao();
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        response.setContentType("text/html; charset=UTF-8");
        List<Cars> result = carsDao.getAll();
        try {
            PrintWriter out = response.getWriter();
            out.println("<h1>Список автомобилей:</h1>");
            for (Cars cars: result)
                out.println("<p>" + cars + "</p>");
        } catch (IOException e) {
            System.out.println(e);
        } /**catch (ServletException e) {
            System.out.println(e);
        }*/
    }
}
