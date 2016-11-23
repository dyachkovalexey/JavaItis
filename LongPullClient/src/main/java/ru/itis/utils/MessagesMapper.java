package ru.itis.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.MessageDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessagesMapper implements RowMapper {

    @Override
    public MessageDto mapRow(ResultSet resultSet, int i) throws SQLException {
        MessageDto messageDto = new MessageDto(resultSet.getString("message_owner"), resultSet.getString("message_text"));
        return messageDto;
    }
}
