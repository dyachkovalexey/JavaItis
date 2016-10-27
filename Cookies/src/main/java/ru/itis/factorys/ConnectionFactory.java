package ru.itis.factorys;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;

    private Properties properties;
    private Connection connection;

    private ConnectionFactory() {
        try {
            properties = new Properties();

            properties.load(new FileInputStream("C:\\Users\\Lo0ny\\Desktop\\JavaItis\\Cookies\\src\\main\\resources\\SQL.properties"));

            Class.forName(properties.getProperty("jdbc.driver"));
            this.connection = DriverManager.getConnection(properties.getProperty("jdbc.URL"),
                    properties.getProperty("jdbc.name"), properties.getProperty("jdbc.password"));

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    static {
        instance = new ConnectionFactory();
    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
