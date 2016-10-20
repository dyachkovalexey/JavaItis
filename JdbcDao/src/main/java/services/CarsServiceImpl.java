package services;

import dao.CarsDao;
import dao.CarsDaoJdbcImpl;
import models.Cars;

import static utils.Verifier.verifyCarExist;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class CarsServiceImpl implements CarsService {

    private CarsDao carsDao;

    public CarsServiceImpl(CarsDao carsDao) {
        this.carsDao = carsDao;
    }

    public void updateCar(Cars cars) {
        verifyCarExist(cars.getCarId());
        this.carsDao.update(cars);
    }
}
