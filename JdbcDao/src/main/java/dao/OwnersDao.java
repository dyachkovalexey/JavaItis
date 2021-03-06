package dao;

import models.Owners;

import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public interface OwnersDao {
    Owners find(int id);
    List<Owners> getAll();
    void delete(int id);
    void update(Owners owners);
    void add(Owners owners);
}
