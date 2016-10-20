package services;

import dao.CarsDao;
import dao.CarsDaoJdbcImpl;
import models.Cars;

import java.util.List;

import static utils.Verifier.verifyCarExist;

public class CarsServiceImpl implements CarsService {

    private CarsDao carsDao;

    public CarsServiceImpl(CarsDao carsDao) {
        this.carsDao = carsDao;
    }

    public Cars findCarById(int id) {
        return carsDao.find(id);
    }

    public void updateCar(Cars cars) {
        verifyCarExist(cars.getCarId());
        this.carsDao.update(cars);
    }

    public List<Cars> getAll() {
        return this.carsDao.getAll();
    }

    public void deleteCar(int id) {
        verifyCarExist(id);
        this.carsDao.delete(id);
    }

    public void addCar(Cars car) {
        this.carsDao.add(car);
    }
}
