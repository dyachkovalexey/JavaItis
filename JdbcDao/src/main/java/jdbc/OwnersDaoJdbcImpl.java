package jdbc;

import connection.SupportFactory;
import dao.OwnersDao;
import models.Owners;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class OwnersDaoJdbcImpl implements OwnersDao {

    // language=SQL
    private static final String SQL_DELETE_FROM_DB = "DELETE FROM car_owner WHERE owner_id = ?";
    // language=SQL
    private static final String SQL_UPDATE_DB = "UPDATE car_owner SET owner_age = ? WHERE owner_id = ?";
    // language=SQL
    private static final String SQL_ADD_TO_DB = "INSERT INTO car_owner (owner_id, fio, owner_age, owner_city) VALUES" +
            " (?, ?, ?, ?)";

    public void find(int id) {
        try {
            Statement statement = SupportFactory.getInstance().getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM car_owner");
            while (result.next()) {
                int autoId = result.getInt("owner_id");
                if (autoId == id) {
                    System.out.println(result.getString("fio"));
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
            ResultSet result = statement.executeQuery("SELECT * FROM car_owner");
            while (result.next()) {
                int ownerId = result.getInt("owner_id");
                String fio = result.getString("fio");
                int ownerAge = result.getInt("ownerAge");
                String city = result.getString("owner_city");

                System.out.println("id - " + ownerId + ", name - " + fio + ", age - " + ownerAge + ", city - " + city);
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
            System.out.println("");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Owners owners) {
        try {
            PreparedStatement statement = SupportFactory.getInstance().getConnection().prepareStatement(SQL_UPDATE_DB);
            statement.setInt(1, owners.getOwnerId());
            statement.setInt(2, owners.getOwnerAge());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void add(Owners owners) {
        try {
            PreparedStatement statement = SupportFactory.getInstance().getConnection().prepareStatement(SQL_ADD_TO_DB);
            statement.setInt(1, owners.getOwnerId());
            statement.setString(2, owners.getFIO());
            statement.setInt(3, owners.getOwnerAge());
            statement.setString(4, owners.getOwnerCity());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
