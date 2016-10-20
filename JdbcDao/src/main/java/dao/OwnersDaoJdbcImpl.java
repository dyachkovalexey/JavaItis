package dao;

import factorys.ConnectSupportFactory;
import dao.OwnersDao;
import models.Owners;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class OwnersDaoJdbcImpl implements OwnersDao {


    private Connection connection;
    // language=SQL
    private static final String SQL_DELETE_FROM_DB = "DELETE FROM car_owner WHERE owner_id = ?";
    // language=SQL
    private static final String SQL_UPDATE_DB = "UPDATE car_owner SET owner_age = ? WHERE owner_id = ?";
    // language=SQL
    private static final String SQL_ADD_TO_DB = "INSERT INTO car_owner (owner_id, fio, owner_age, owner_city) VALUES" +
            " (?, ?, ?, ?)";

    public OwnersDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public void find(int id) {
        try {
            Statement statement = ConnectSupportFactory.getInstance().getConnection().createStatement();
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

    public List getAll() {
        try {
            ArrayList owners = new ArrayList();
            Statement statement = ConnectSupportFactory.getInstance().getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM car_owner");
            while (result.next()) {
                int ownerId = result.getInt("owner_id");
                String fio = result.getString("fio");
                int ownerAge = result.getInt("owner_age");
                String city = result.getString("owner_city");

                //System.out.println("id - " + ownerId + ", name - " + fio + ", age - " + ownerAge + ", city - " + city);

                owners.add( "id - " + ownerId + ", name - " + fio + ", age - " + ownerAge + ", city - " + city);
            }
            return owners;
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
            System.out.println("");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Owners owners) {
        try {
            PreparedStatement statement = ConnectSupportFactory.getInstance().getConnection().prepareStatement(SQL_UPDATE_DB);
            statement.setInt(1, owners.getOwnerId());
            statement.setInt(2, owners.getOwnerAge());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void add(Owners owners) {
        try {
            PreparedStatement statement = ConnectSupportFactory.getInstance().getConnection().prepareStatement(SQL_ADD_TO_DB);
            statement.setInt(1, owners.getOwnerId());
            statement.setString(2, owners.getFIO());
            statement.setInt(3, owners.getOwnerAge());
            statement.setString(4, owners.getOwnerCity());
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
