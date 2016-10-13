package factorys;

import dao.CarsDao;
import dao.OwnersDao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class DaoSupportFactory {
    private static DaoSupportFactory instance;

    private Properties properties;
    private OwnersDao ownersDao;
    private CarsDao carsDao;

    private  DaoSupportFactory() {
        this.properties = new Properties();

        try {
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\dao.properties"));

            String carsDaoClassName = properties.getProperty("carsdao.class");
            String ownersDaoClassName = properties.getProperty("ownersdao.class");

            Class carsClass = Class.forName(carsDaoClassName);
            Class ownersClass = Class.forName(ownersDaoClassName);

            Constructor carsConstructor = carsClass.getConstructor(Connection.class);
            Constructor ownersConstructor = ownersClass.getConstructor(Connection.class);

            this.ownersDao = (OwnersDao)ownersConstructor.newInstance(ConnectSupportFactory.getInstance().getConnection());
            this.carsDao = (CarsDao)carsConstructor.newInstance(ConnectSupportFactory.getInstance().getConnection());

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static {
        instance = new DaoSupportFactory();
    }

    public static DaoSupportFactory getInstance() {
        return instance;
    }

    public OwnersDao getOwnersDao() {
        return  this.ownersDao;
    }

    public CarsDao getCarsDao() {
        return this.carsDao;
    }
}
