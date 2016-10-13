package factorys;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class ConnectSupportFactory {

    private static ConnectSupportFactory instance;

    private Properties properties;
    private Connection connection;

    private ConnectSupportFactory() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\SQL.properties"));

            Class.forName(properties.getProperty("jdbc.driver"));
            this.connection = null;

            this.connection = DriverManager.getConnection(properties.getProperty("jdbc.URL"),
            properties.getProperty("jdbc.name"),
            properties.getProperty("jdbc.password"));

        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    static {
        instance = new ConnectSupportFactory();
    }

    public static ConnectSupportFactory getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
