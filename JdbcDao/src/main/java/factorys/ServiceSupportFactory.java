package factorys;

import dao.CarsDao;
import dao.OwnersDao;
import dao.CarsDaoJdbcImpl;
import dao.OwnersDaoJdbcImpl;
import services.CarsService;
import services.OwnersService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class ServiceSupportFactory {

    private static ServiceSupportFactory instance;

    private Properties properties;
    private CarsService carsService;
    private OwnersService ownersService;

    private ServiceSupportFactory() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\JdbcDao\\src\\main\\resources\\service.properties"));

            String carsServiceClassName = properties.getProperty("carsimpl.class");
            String ownersServiceClassName = properties.getProperty("ownersimpl.class");

            Class carsServiceClass = Class.forName(carsServiceClassName);
            Class ownersServiceClass = Class.forName(ownersServiceClassName);

            Constructor carsConstructor = carsServiceClass.getConstructor(CarsDao.class);
            Constructor ownersConstrucor = ownersServiceClass.getConstructor(OwnersDao.class);

            this.carsService = (CarsService) carsConstructor.newInstance(DaoSupportFactory.getInstance().getCarsDao());
            this.ownersService = (OwnersService) ownersConstrucor.newInstance(DaoSupportFactory.getInstance().getOwnersDao());

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (NoSuchMethodException e) {
            System.out.println(e);
        } catch (InvocationTargetException e) {
            System.out.println(e);
        } catch (IllegalAccessException e) {
            System.out.println(e);
        } catch (InstantiationException e) {
            System.out.println(e);
        }
    }

    static {
        instance = new ServiceSupportFactory();
    }

    public static ServiceSupportFactory getInstance() {
        return instance;
    }

    public OwnersService getOwnersService() {
        return this.ownersService;
    }

    public CarsService getCarsService() {
        return this.carsService;
    }
}
