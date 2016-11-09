package ru.itis.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.dao.UserDao;
import ru.itis.models.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@WebFilter
public class LogFilter implements Filter {

    @Autowired
    private UserDao userDao;

    public void init(FilterConfig filterConfig) throws ServletException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("CookieBeans.xml");
        this.userDao = (UserDao)applicationContext.getBean("userDao");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        try {
            servletResponse.setContentType("text/html; charset=UTF-8");
            Cookie cookie[] = ((HttpServletRequest) servletRequest).getCookies();
            if (cookie != null) {
                for (int i = cookie.length-1; i > 0; i--) {
                    try {
                        Users users = userDao.findByToken(cookie[i].getValue());

                    if (users != null) {
                        String token = users.getUserToken();

                        if (cookie[i].getValue().equals(token)) {
                            filterChain.doFilter(servletRequest, servletResponse);
                            break;
                        }
                    }
                    } catch (NullPointerException e) {}
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
