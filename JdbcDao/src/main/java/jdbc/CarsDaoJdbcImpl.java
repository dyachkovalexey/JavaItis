package jdbc;

import connection.SupportFactory;
import dao.CarsDao;
import models.Cars;

import java.sql.*;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoJdbcImpl implements CarsDao {

    // language=SQL
    private static final String SQL_DELETE_FROM_DB = "DELETE FROM auto WHERE auto_id = ?";
    // language=SQL
    private static final String SQL_UPDATE_DB = "UPDATE auto SET mileage = ? WHERE auto_id = ?";
    // language=SQL
    private static final String SQL_ADD_TO_DB = "INSERT INTO auto (auto_id, auto_name, mileage) VALUES (?, ?, ?);";

    public void find(int id) {
        try {
            Statement statement = SupportFactory.getInstance().getConnection().createStatement();
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

    public void getAll() {
        try {
            Statement statement = SupportFactory.getInstance().getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM auto ");
            while (result.next()) {
                int autoId = result.getInt("auto_id");
                String autoName = result.getString("auto_name");
                int mileage = result.getInt("mileage");

                System.out.println("id - " + autoId + ", name - " + autoName + ", mileage - " + mileage);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement = SupportFactory.getInstance().getConnection().prepareStatement(SQL_DELETE_FROM_DB);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void update(Cars cars) {
        try {
            PreparedStatement statement = SupportFactory.getInstance().getConnection().prepareStatement(SQL_UPDATE_DB);
            statement.setInt(1, cars.getMileage());
            statement.setInt(2, cars.getCarId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void add(Cars cars) {
        try {
            PreparedStatement statement = SupportFactory.getInstance().getConnection().prepareStatement(SQL_ADD_TO_DB);
            statement.setInt(1, cars.getCarId());
            statement.setString(2, cars.getAutoName());
            statement.setInt(3, cars.getMileage());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
