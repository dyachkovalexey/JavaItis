package filters;

import dao.UserDao;
import factorys.DaoFactory;
import models.Users;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LogFilter implements Filter {

    private String messageParam;
    private UserDao userDao;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.messageParam = filterConfig.getInitParameter("message-param");
        userDao = DaoFactory.getInstance().getOwnersDao();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        try {
            servletResponse.setContentType("text/html; charset=UTF-8");
            Cookie cookie[] = ((HttpServletRequest) servletRequest).getCookies();
            if (cookie != null) {
                for (int i = cookie.length-1; i > 0; i--) {
                    Users users = userDao.findByToken(cookie[i].getValue());
                    if (users != null) {
                        String token = users.getUserToken();

                        if (cookie[i].getValue().equals(token)) {
                            filterChain.doFilter(servletRequest, servletResponse);
                            break;
                        }
                    }
                }
                servletResponse.getWriter().println("Нет доступа к странице, пожалуйста, авторизуйтесь");
            }
        } catch (IOException e) {
            System.out.println(e);
        } catch (ServletException e) {
            System.out.println(e);
        }

    }

    public void destroy() {

    }
}
