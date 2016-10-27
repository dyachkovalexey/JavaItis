package ru.itis.dao;

import ru.itis.models.Autos;

import java.util.List;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public interface AutoDao {
    List<Autos> getAll();
    void add(Autos autos);
    Autos find(int id);
}
