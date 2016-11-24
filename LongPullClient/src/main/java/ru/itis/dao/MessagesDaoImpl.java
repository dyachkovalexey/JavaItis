package ru.itis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.MessageDto;
import ru.itis.utils.MessagesMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public class MessagesDaoImpl implements  MessagesDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_ADD_TEXT_TO_DB = "INSERT INTO messagesdb (message_text, message_owner) VALUES (:text, :owner)";
    //language=SQL
    public static final String SQL_GET_TEXT_FROM_DB = "SELECT * FROM messagesdb WHERE message_id > :currentId";

    @Autowired
    public MessagesDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public MessagesDaoImpl() {
    }

    @Override
    public void add(MessageDto messageDto) {
        Map map = new HashMap();
        map.put("text", messageDto.getText());
        map.put("owner", messageDto.getFrom());
        namedParameterJdbcTemplate.update(SQL_ADD_TEXT_TO_DB, map);
    }

    @Override
    public List<MessageDto> select(int currentMessageId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("currentId", currentMessageId);
        List<MessageDto> messages = (List<MessageDto>)namedParameterJdbcTemplate.queryForObject(
                SQL_GET_TEXT_FROM_DB, sqlParameterSource, new MessagesMapper());
        return messages;
    }
}
