package connection;

import dao.CarsDao;
import dao.OwnersDao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLData;
import java.util.Properties;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class SupportFactory {

    private static  SupportFactory instance;

    private Properties properties;
    private MyConnection myConnection;

    private SupportFactory() {
        properties = new Properties();

        try {
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\SQL.properties"));

            String connectionClass = properties.getProperty("connection.class");

            this.myConnection = (MyConnection)Class.forName(connectionClass).newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static {
        instance = new SupportFactory();
    }

    public static SupportFactory getInstance() {
        return instance;
    }

    public MyConnection getMyConnection() {
        return myConnection;
    }
}
