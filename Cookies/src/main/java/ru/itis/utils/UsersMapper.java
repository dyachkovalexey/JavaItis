package ru.itis.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper {

    public Users mapRow(ResultSet resultSet, int i) throws SQLException {
        Users user = new Users(resultSet.getInt("user_id"), resultSet.getString("user_name"),
                resultSet.getString("user_login"), resultSet.getInt("user_password"), resultSet.getString("user_token"));
        return user;
    }
}

