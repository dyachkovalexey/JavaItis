package ru.itis.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper {
    @Override
    public Users mapRow(ResultSet resultSet, int i) throws SQLException {
        Users users = new Users(resultSet.getInt("user_id"), resultSet.getString("user_name"), resultSet.getString("user_login"), resultSet.getInt("user_password"));
        return users;
    }
}
