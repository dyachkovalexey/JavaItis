package servlets;

import dao.CarsDao;
import dao.CarsDaoJdbcImpl;
import factorys.ConnectSupportFactory;
import factorys.DaoSupportFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Lo0ny on 19.10.2016.
 */
public class CarsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        response.setContentType("text/html");
        try {
            Statement statement = ConnectSupportFactory.getInstance().getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM auto ");
            PrintWriter out = null;
            out = response.getWriter();
            while (result.next()) {
                String message = result.getString("auto_name");
                out.println("<p>" + message + "</p>");
            }
        } catch (IOException e) {
            System.out.println(e);
        }  catch (SQLException e) {
            System.out.println(e);
        }

    }
}
