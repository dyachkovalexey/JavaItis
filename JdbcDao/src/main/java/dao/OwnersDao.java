package dao;

import models.Owners;

import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public interface OwnersDao {
    void find(int id);
    List getAll();
    void delete(int id);
    void update(Owners owners);
    void add(Owners owners);
}
