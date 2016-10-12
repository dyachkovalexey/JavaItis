package dao;

import models.Cars;

/**
 * Created by KFU-user on 12.10.2016.
 */
public interface CarsDao {
    void find(int id);
    void getAll();
    void delete(int id);
    void update(Cars cars);
    void add(Cars cars);
}
