package ru.itis.dao;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.itis.dto.MessageDto;
import ru.itis.models.Messages;
import ru.itis.models.Users;
import ru.itis.utils.MessageMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesDaoImpl implements  MessagesDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_FIND_ALL = "SELECT * FROM message";
    //language=SQL
    public static final String SQL_FIND_BY_ID = "SELECT * FROM message WHERE message_id=:messageId";
    //language=SQL
    public static final String SQL_FIND_ALL_BY_CHAT_ID = "SELECT * FROM message WHERE chat_id=:chatId";
    //language=SQL
    public static final String SQL_SAVE = "INSERT INTO message (message_text, user_id, chat_id) VALUES " +
            "(:messageText, :userId, :chatId)";
    //language=SQL
    public static final String SQL_UPDATE ="UPDATE user_message SET last_message_id=:messageId WHERE " +
            "user_id=:userId AND chat_id=:chatId";

    @Autowired
    public MessagesDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public MessagesDaoImpl() {
    }

    @Override
    public List<Messages> findAll() {
        List<Messages> messages = (List<Messages>)namedParameterJdbcTemplate.query(SQL_FIND_ALL, new MessageMapper());
        return messages;
    }

    @Override
    public Messages find(Integer messageId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("messageId", messageId);
        Messages messages = (Messages)namedParameterJdbcTemplate.queryForObject
                (SQL_FIND_BY_ID, sqlParameterSource, new MessageMapper());
        return messages;
    }

    @Override
    public List<MessageDto> findAllByChatId(Integer chatId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("chatId", chatId);
        List<MessageDto> messages = (List<MessageDto>)namedParameterJdbcTemplate.queryForObject
                (SQL_FIND_ALL_BY_CHAT_ID, sqlParameterSource, new MessageMapper());
        return messages;
    }

    @Override
    public void save(MessageDto messageDto, Integer userId) {
        Map map = new HashMap();
        map.put("messageText", messageDto.getText());
        map.put("userId", userId);
        map.put("chatId", messageDto.getChatId());
        namedParameterJdbcTemplate.update(SQL_SAVE, map);
    }

    @Override
    public void update(Messages messages) {
        Map map = new HashMap();
        map.put("messageId", messages.getMessageId()); //TODO: ??
        map.put("userId", messages.getUserId());
        map.put("chatId", messages.getChatId());
        namedParameterJdbcTemplate.update(SQL_UPDATE, map);
    }
}
