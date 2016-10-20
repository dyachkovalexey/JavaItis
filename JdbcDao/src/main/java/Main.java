import dao.CarsDao;
import dao.CarsDaoJdbcImpl;
import dao.OwnersDao;
import factorys.DaoSupportFactory;
import factorys.ServiceSupportFactory;
import models.Owners;
import services.OwnersService;

import java.security.acl.Owner;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Main {
    public static void main(String[] args) {


        OwnersDao ownersDao = DaoSupportFactory.getInstance().getOwnersDao();
        CarsDao carsDao = DaoSupportFactory.getInstance().getCarsDao();


        System.out.println(ownersDao.find(1).toString());
        //ownersDao.getAll();
        //System.out.println();
        //carsDao.getAll();
    }
}
