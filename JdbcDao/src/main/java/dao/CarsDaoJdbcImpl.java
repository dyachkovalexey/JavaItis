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

    //language=SQL
    private static final String SQL_GET_ALL = "SELECT * FROM auto";
    // language=SQL
    private static final String SQL_FIND_CARS = "SELECT * FROM auto WHERE auto_id = ?";
    // language=SQL
    private static final String SQL_DELETE_FROM_DB = "DELETE FROM auto WHERE auto_id = ?";
    // language=SQL
    private static final String SQL_UPDATE_DB = "UPDATE auto SET mileage = ? WHERE auto_id = ?";
    // language=SQL
    private static final String SQL_ADD_TO_DB = "INSERT INTO auto (auto_id, auto_name, mileage) VALUES (?, ?, ?);";

    public CarsDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public Cars find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CARS);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Cars cars = new Cars(result.getInt("auto_id"), result.getString("auto_name"), result.getInt("mileage"));
            return cars;

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public List getAll() {
        try {
            List<Cars> cars = new ArrayList<Cars>();
            Statement statement = ConnectSupportFactory.getInstance().getConnection().createStatement();
            ResultSet result = statement.executeQuery(SQL_GET_ALL);
            while (result.next()) {
                Cars car = new Cars(result.getInt("auto_id"), result.getString("auto_name"), result.getInt("mileage"));
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            throw new IllegalArgumentException();
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
