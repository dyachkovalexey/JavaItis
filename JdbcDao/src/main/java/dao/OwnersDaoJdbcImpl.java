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
    private static final String SQL_FIND_OWNERS = "SELECT * FROM car_owner WHERE owner_id = ?";
    // language=SQL
    private static final String SQL_GET_ALL = "SELECT * FROM car_owner";
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

    public Owners find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_OWNERS);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();

            Owners owners = new Owners(result.getInt("owner_id"), result.getString("fio"),
                    result.getInt("owner_age"), result.getString("owner_city"));
            return owners;

        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public List getAll() {
        try {
            List<Owners> owners = new ArrayList<Owners>();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_GET_ALL);
            while (result.next()) {
                Owners owner = new Owners(result.getInt("owner_id"), result.getString("fio"),
                        result.getInt("owner_age"), result.getString("owner_city"));
                owners.add(owner);
            }
            return owners;
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FROM_DB);
            statement.setInt(1, id);
            statement.execute();
            System.out.println("");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void update(Owners owners) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DB);
            statement.setInt(1, owners.getOwnerId());
            statement.setInt(2, owners.getOwnerAge());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void add(Owners owners) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_TO_DB);
            statement.setInt(1, owners.getOwnerId());
            statement.setString(2, owners.getFIO());
            statement.setInt(3, owners.getOwnerAge());
            statement.setString(4, owners.getOwnerCity());
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
