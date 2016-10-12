package connection;

import dao.CarsDao;
import dao.OwnersDao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLData;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class SupportFactory {

    private static  SupportFactory instance;

    private Properties properties;
    private Connection connection;

    private SupportFactory() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\Lo0ny\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\SQL.properties"));

            Class.forName("org.postgresql.Driver");
            this.connection = null;

            this.connection = DriverManager.getConnection(properties.getProperty("URL"),
            properties.getProperty("name"),
            properties.getProperty("password"));

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    static {
        instance = new SupportFactory();
    }

    public static SupportFactory getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
