package dao;

import factorys.ConnectSupportFactory;
import dao.CarsDao;
import models.Cars;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoJdbcImpl implements CarsDao {

    private Connection connection;

    // language=SQL
    private static final String SQL_DELETE_FROM_DB = "DELETE FROM auto WHERE auto_id = ?";
    // language=SQL
    private static final String SQL_UPDATE_DB = "UPDATE auto SET mileage = ? WHERE auto_id = ?";
    // language=SQL
    private static final String SQL_ADD_TO_DB = "INSERT INTO auto (auto_id, auto_name, mileage) VALUES (?, ?, ?);";

    public CarsDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public void find(int id) {
        try {
            Statement statement = ConnectSupportFactory.getInstance().getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM auto");
            while (result.next()) {
                int autoId = result.getInt("auto_id");
                if (autoId == id) {
                    System.out.println(result.getString("auto_name"));
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List getAll() {
        try {
            ArrayList cars = new ArrayList();
            Statement statement = ConnectSupportFactory.getInstance().getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM auto ");
            while (result.next()) {
                int autoId = result.getInt("auto_id");
                String autoName = result.getString("auto_name");
                int mileage = result.getInt("mileage");
                System.out.println("id - " + autoId + ", name - " + autoName + ", mileage - " + mileage);
                cars.add("id - " + autoId + ", name - " + autoName + ", mileage - " + mileage);
            }
            return cars;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement = ConnectSupportFactory.getInstance().getConnection().prepareStatement(SQL_DELETE_FROM_DB);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void update(Cars cars) {
        try {
            PreparedStatement statement = ConnectSupportFactory.getInstance().getConnection().prepareStatement(SQL_UPDATE_DB);
            statement.setInt(1, cars.getMileage());
            statement.setInt(2, cars.getCarId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void add(Cars cars) {
        try {
            PreparedStatement statement = ConnectSupportFactory.getInstance().getConnection().prepareStatement(SQL_ADD_TO_DB);
            statement.setInt(1, cars.getCarId());
            statement.setString(2, cars.getAutoName());
            statement.setInt(3, cars.getMileage());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
