package servlets;

import dao.OwnersDao;
import factorys.DaoSupportFactory;
import models.Owners;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class OwnersServlet extends HttpServlet{

    private OwnersDao ownersDao;

    @Override
    public  void init() throws ServletException {
        super.init();
        ownersDao = DaoSupportFactory.getInstance().getOwnersDao();
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        try {
            response.setContentType("text/html; charset=UTF-8");

            List<Owners> owners = ownersDao.getAll();
            request.setAttribute("CarOwners", owners);

            getServletContext().getRequestDispatcher("/owners.jsp").forward(request, response);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ServletException e) {
            System.out.println(e);
        }

    }

    @Override
    public void doPost(HttpServletRequest request,
                          HttpServletResponse response) {
        try {

            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");

            /*Enumeration ownerEnum = request.getParameterNames();
            String[] count = new String[4];
            int i = 0;

            while(ownerEnum.hasMoreElements())
            {
                String[] values = request.getParameterValues((String)ownerEnum.nextElement());
                count[i] = values[0];
                i++;
            }

            Owners owner = new Owners(Integer.parseInt(count[0]), count[1], Integer.parseInt(count[2]), count[3]);*/

            String ownerName = request.getParameter("ownerName");
            int ownerAge = Integer.parseInt(request.getParameter("ownerAge"));
            String ownerCity = request.getParameter("ownerCity");

            Owners owner = new Owners(ownerName, ownerAge, ownerCity);


            if (owner != null) {
                ownersDao.add(owner);
            } else {
                request.setAttribute("error", "Unknown user, please try again");
                request.getRequestDispatcher("/owners.jsp").forward(request, response);
            }
            doGet(request,response);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        } catch (ServletException e) {
            throw new IllegalArgumentException();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
