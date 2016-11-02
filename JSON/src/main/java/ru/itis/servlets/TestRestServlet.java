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
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KFU-user on 02.11.2016.
 */
public class TestRestServlet extends HttpServlet {

    private UserDao userDao;
    private AutoDao autoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
        this.autoDao = (AutoDao)applicationContext.getBean("autoDao");
    }

    private class RestRequest {
        private Pattern regExAllPattern = Pattern.compile("/users");
        private Pattern regExUsersLoginPattern = Pattern.compile("/users?id=([0-9]+)");
        private Pattern regExAllAutosPattern = Pattern.compile("/users/([0-9]+)/autos");

        private List<Users> users;
        private Users user;
        private List<Autos> autos;


        public RestRequest(String pathInfo) {
            Matcher matcher;

            matcher = regExAllPattern.matcher(pathInfo);
            if (matcher.find()) {
                this.users = userDao.getAll();
                return;
            }

            matcher = regExUsersLoginPattern.matcher(pathInfo);
            if (matcher.find()) {
                this.user = userDao.findById(Integer.parseInt(matcher.group(1)));
                return;
            }

            matcher = regExAllAutosPattern.matcher(pathInfo);
            if (matcher.find()) {
                this.autos = autoDao.getAllByUserId(Integer.parseInt(matcher.group(1)));
                return;
            }
            }


        public List<Users> getUsers() {
            return users;
        }

        public Users getUser() {
            return user;
        }

        public List<Autos> getAutos() {
            return autos;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();

        printWriter.println(request.getPathInfo());
        printWriter.println(request.getParameterMap());
        RestRequest restRequest = new RestRequest(request.getPathInfo());
        if (request.getParameter("id")!=null)
            printWriter.println();
        if (restRequest.getUser()!=null)
            printWriter.println(restRequest.getUser().toString());
        if (restRequest.getAutos()!=null)
            printWriter.println(restRequest.getAutos().toString());
        if (restRequest.getUsers()!=null)
            printWriter.println(restRequest.getUsers().toString());
        response.setContentType("application/json");
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
