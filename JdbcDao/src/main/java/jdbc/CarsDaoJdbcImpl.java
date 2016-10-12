package jdbc;

import connection.MyConnection;
import connection.SupportFactory;
import dao.CarsDao;
import models.Cars;

import java.sql.*;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoJdbcImpl implements CarsDao {


    private String URL = "jdbc:postgresql://localhost:5432/Auto";
    private String name = "postgres";
    private String password = "jie1995xa";




    public CarsDaoJdbcImpl() {
        Connection connection = null;

        try {
            Class.forName("org.postgressql.Driver");
            connection = DriverManager.getConnection(this.URL, this.name, this.password);

            Statement statement = (Statement) SupportFactory.getInstance().getMyConnection();



        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }

    }


    public void find(int id) {
        new CarsDaoJdbcImpl();
        Statement statement = (Statement) SupportFactory.getInstance().getMyConnection();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM auto WHERE auto.auto_id = id");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void getAll() {

    }

    public void delete(int id) {

    }

    public void update(Cars cars) {

    }

    public void add(Cars cars) {

    }
}
