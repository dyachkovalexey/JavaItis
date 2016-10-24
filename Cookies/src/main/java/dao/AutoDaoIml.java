package dao;

import models.Autos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class AutoDaoIml implements  AutoDao {

    private Connection connection;

    //language=SQL
    public static final String SQL_GET_ALL = "SELECT * FROM auto";
    //language=SQL
    public static final String SQL_ADD = "INSERT INTO auto (auto_name, auto_number, user_id) VALUES " +
            "(?,?,?)";
    //language=SQL
    public static final String SQL_FIND = "SELECT * FROM auto WHERE auto_id=?";

    public AutoDaoIml(Connection connection) {
        this.connection = connection;
    }

    public List<Autos> getAll() {
        try {
            List<Autos> autos = new ArrayList<Autos>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            while (resultSet.next()) {
                Autos auto = new Autos(resultSet.getInt("auto_id"), resultSet.getString("auto_name"),
                        resultSet.getString("auto_number"), resultSet.getInt("user_id"));
                autos.add(auto);
            }
            return autos;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public void add(Autos autos) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD);
            preparedStatement.setString(1, autos.getAutoName());
            preparedStatement.setString(2, autos.getAutoNumber());
            preparedStatement.setInt(3, autos.getUserId());

            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Autos find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Autos autos = new Autos(resultSet.getInt("auto_id"), resultSet.getString("auto_name"),
                    resultSet.getString("auto_number"), resultSet.getInt("user_id"));
            return autos;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
