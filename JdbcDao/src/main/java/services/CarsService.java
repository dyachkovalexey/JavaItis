package services;

import models.Cars;

import java.util.List;

public interface CarsService {
    Cars findCarById(int id);
    void updateCar(Cars cars);

    List<Cars> getAll();
    void deleteCar(int id);
    void addCar(Cars car);

}
