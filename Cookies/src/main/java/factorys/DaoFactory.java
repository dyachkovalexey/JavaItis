package factorys;

import dao.AutoDao;
import dao.UserDao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;


public class DaoFactory {
    private static DaoFactory instance;

    private Properties properties;
    private AutoDao autoDao;
    private UserDao userDao;

    private DaoFactory() {
        try {
            this.properties = new Properties();

            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\Cookies\\src\\main\\resources\\dao.properties"));

            String autoDaoName = properties.getProperty("autodao.class");
            String userDaoName = properties.getProperty("userdao.class");

            Class autoClass = Class.forName(autoDaoName);
            Class userClass = Class.forName(userDaoName);

            Constructor autoConstructor = autoClass.getConstructor(Connection.class);
            Constructor userConstructor = userClass.getConstructor(Connection.class);

            this.autoDao = (AutoDao)autoConstructor.newInstance(ConnectionFactory.getInstance().getConnection());
            this.userDao = (UserDao)userConstructor.newInstance(ConnectionFactory.getInstance().getConnection());

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            System.out.println(e);
        }
    }

    static {
        instance = new DaoFactory();
    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public UserDao getOwnersDao() {
        return  this.userDao;
    }

    public AutoDao getAutoDao() {
        return this.autoDao;
    }
}
