package ru.itis.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.configuration.WebConfig;
import ru.itis.dao.UserDao;
import ru.itis.dao.UserDaoImpl;
import ru.itis.models.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static ru.itis.utils.Verifier.verifyUserExistByToken;


public class LogFilter implements Filter {

    private String messageParam;


    private UserDao userDao;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.messageParam = filterConfig.getInitParameter("message-param");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain){
        try {
            servletResponse.setContentType("text/html; charset=UTF-8");
            Cookie cookie[] = ((HttpServletRequest) servletRequest).getCookies();
            if (cookie != null) {
                for (int i = cookie.length-1; i > 0; i--) {
                    try {
                        System.out.println(cookie[i].getValue());
                        Users users = userDao.findByToken(cookie[i].getValue());
                    if (users != null) {
                        String token = users.getUserToken();

                        if (cookie[i].getValue().equals(token)) {
                            filterChain.doFilter(servletRequest, servletResponse);
                            break;
                        }
                    }
                    } catch (NullPointerException e) {
                        System.out.println(e);
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
