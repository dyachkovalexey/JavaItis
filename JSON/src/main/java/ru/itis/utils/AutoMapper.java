package ru.itis.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Autos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoMapper implements RowMapper {
    public Autos mapRow(ResultSet resultSet, int i) throws SQLException {
        Autos autos = new Autos(resultSet.getInt("auto_id"), resultSet.getString("auto_name"),
                resultSet.getString("auto_number"), resultSet.getInt("user_id"));
        return autos;
    }
}
