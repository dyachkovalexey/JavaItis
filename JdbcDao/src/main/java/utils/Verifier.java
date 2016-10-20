package utils;



import factorys.ConnectSupportFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verifier {

    private static Connection connection;

    static {
        connection = ConnectSupportFactory.getInstance().getConnection();
    }
    // language=SQL
    private static final String SQL_FIND_USER = "SELECT * FROM car_owner WHERE id = ?;";
    // language=SQL
    private static final String SQL_FIND_CAR = "SELECT * FROM auto WHERE id = ?;";

    public static void verifyOwnerExist(int userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER);
            preparedStatement.setInt(1, userId);

            ResultSet result = preparedStatement.executeQuery();

            if (!result.next()) {
                throw new IllegalArgumentException("USER_NOT_FOUND");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void verifyCarExist(int carId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_CAR);
            preparedStatement.setInt(1, carId);

            ResultSet result = preparedStatement.executeQuery();

            if(!result.next()) {
                throw new IllegalArgumentException("CAR_NOT_FOUND");
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
