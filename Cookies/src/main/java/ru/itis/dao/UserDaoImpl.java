package ru.itis.dao;

import ru.itis.models.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    private Connection connection;

    //language=SQL
    public static final String SQL_GET_ALL = "SELECT * FROM users";
    //language=SQL
    public static final String SQL_REGISTRATION = "INSERT INTO users (user_name, user_login, user_password, user_token) VALUES " +
            "(?,?,?, ?)";
    //language=SQL
    public static final String SQL_FIND = "SELECT * FROM users WHERE user_login=?";
    //language=SQL
    public static final String SQL_FIND_BY_TOKEN = "SELECT * FROM users WHERE user_token=?";
    //language=SQL
    private static final String SQL_UPDATE_USERS = "UPDATE users SET user_token = ? WHERE user_id=?;";

    public UserDaoImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public UserDaoImpl() {
    }

    public List<Users> getAll() {
        try {

            List<Users> users = new ArrayList<Users>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL);
            while (resultSet.next()) {
                Users user = new Users(resultSet.getInt("user_id"), resultSet.getString("user_name"),
                        resultSet.getString("user_login"), resultSet.getInt("user_password"), resultSet.getString("user_token"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println(e + " getAllUsers");
            return null;
        }
    }

    public void update(String token, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USERS);
            preparedStatement.setString(1, token);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String registration(Users users) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_REGISTRATION);
            preparedStatement.setString(1, users.getUserName());
            preparedStatement.setString(2, users.getUserLogin());
            preparedStatement.setInt(3, users.getUserPassword());
            preparedStatement.setString(4, "");
            preparedStatement.execute();

            return "Пользователь " + users.getUserName() + " успешно зарегестрирован!";
        } catch (SQLException e) {
            System.out.println(e);
            return "Ошибка регистрации";
        }
    }

    public Users find(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Users users = new Users(resultSet.getInt("user_id"), resultSet.getString("user_name"),
                    resultSet.getString("user_login"), resultSet.getInt("user_password"),
                    resultSet.getString("user_token"));
            return users;
        } catch (SQLException e) {
            System.out.println(e + " findlogin");
            return null;
        }
    }

    public Users findByToken(String token) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_TOKEN);
            preparedStatement.setString(1, token);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Users user = new Users(resultSet.getInt("user_id"), resultSet.getString("user_name"),
                    resultSet.getString("user_login"), resultSet.getInt("user_password"),
                    resultSet.getString("user_token"));
            return user;
        } catch (SQLException e) {
            System.out.println(e + " findtoken");
            return null;
        }
    }
}
