package ru.itis.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Messages;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MessageMapper implements RowMapper {

    @Override
    public Messages mapRow(ResultSet resultSet, int i) throws SQLException {
        Messages messages = new Messages(resultSet.getInt("message_id"), resultSet.getString("message_text"), resultSet.getInt("user_id"), resultSet.getInt("chat_id"));
        return messages;
    }
}
