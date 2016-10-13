package services;

import dao.CarsDao;
import dao.CarsDaoJdbcImpl;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class CarsServiceImpl implements CarsService {

    private CarsDao carsDao;

    public CarsServiceImpl(CarsDao carsDao) {
        this.carsDao = carsDao;
    }
}
