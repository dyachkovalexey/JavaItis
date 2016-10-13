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
        //CarsDaoJdbcImpl carsDaoJdbc = new CarsDaoJdbcImpl();
        //carsDaoJdbc.find(1);
        //carsDaoJdbc.getAll();
        //carsDaoJdbc.delete(1);
        //Cars cars = new Cars(1, "Audi", 500);
        //carsDaoJdbc.update(cars);


        OwnersDao ownersDao = DaoSupportFactory.getInstance().getOwnersDao();

        ownersDao.find(1);
    }
}
