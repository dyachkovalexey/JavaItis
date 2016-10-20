package services;

import models.Owners;

import java.util.List;

public interface OwnersService {

    Owners findCarById(int id);
    void updateCar(Owners owners);

    List<Owners> getAll();
    void deleteCar(int id);
    void addCar(Owners owners);
}
