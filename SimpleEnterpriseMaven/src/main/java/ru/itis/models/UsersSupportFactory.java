package ru.itis.models;

import ru.itis.dao.UsersDao;
import ru.itis.service.SimpleUsersService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UsersSupportFactory {

    private static UsersSupportFactory instance;

    private Properties properties;

    private UsersDao usersDao;
    private SimpleUsersService simpleUsersService;

    private UsersSupportFactory() {
        properties = new Properties();

        try {
            properties.load(new FileInputStream("C:\\Users\\Lo0ny\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\src\\main\\resources\\users.properties"));

            String usersDaoClass = properties.getProperty("user.class");
            String simpleUsersService = properties.getProperty("service.class");

            this.usersDao = (UsersDao)Class.forName(usersDaoClass).newInstance();
            //this.simpleUsersService = (SimpleUsersService)Class.forName(simpleUsersService).newInstance();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException();
        }
    }

    static {
        instance = new UsersSupportFactory();
    }

    public static UsersSupportFactory getInstance() {
        return instance;
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public SimpleUsersService getSimpleUsersService() {
        return simpleUsersService;
    }
}
