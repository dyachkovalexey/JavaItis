package ru.itis.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Lo0ny on 26.10.2016.
 */
public class SpringCookieBeansListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:CookieBeans.xml");
        System.out.println(applicationContext);
        applicationContext.getBean("LoginServlet");
        servletContextEvent.getServletContext().setAttribute("cookieBeans", applicationContext);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
