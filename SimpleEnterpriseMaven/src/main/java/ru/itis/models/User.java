package ru.itis.models;

import java.util.List;

/**
 * Created by admin on 06.10.2016.
 */
public class User {
    private List<Auto> autos;
    private String name;
    private String password;
    private int age;
    private int id;

    public User(String name, String password, int age, int id) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getAge() {
        return age;
    }

    public int getId() { return  id; }
}
