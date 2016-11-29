package ru.itis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.itis.models.Chat;
import ru.itis.utils.ChatMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Service
public class ChatDaoImpl implements  ChatDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_FIND_ALL = "SELECT * FROM chat";
    //language=SQL
    public static final String SQL_SAVE = "INSERT INTO chat (chat_name, user_id) VALUES (:chatName, :userId)";
    //language=SQL
    public static final String SQL_FIND = "SELECT * FROM chat WHERE chat_id=:chatId";
    //language=SQL
    public static final String SQL_UPDATE_NAME = "UPDATE chat SET chat_name=:chatName WHERE chat_id=:chatId";
    //language=SQL
    public static final String SQL_FIND_USER_BY_TOKEN = "SELECT user_id FROM chat_auth WHERE token=:token";

    @Autowired
    public ChatDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public ChatDaoImpl() {
    }

    @Override
    public List<Chat> findAll() {
        List<Chat> chat = namedParameterJdbcTemplate.query(SQL_FIND_ALL, new ChatMapper());
        return chat;
    }

    @Override
    public Chat find(int chatId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("chatId", chatId);
        Chat chat = (Chat)namedParameterJdbcTemplate.queryForObject(SQL_FIND, sqlParameterSource, new ChatMapper());
        return chat;
    }

    @Override
    public void save(Chat chat) {
        Map map = new HashMap<>();
        map.put("chatName", chat.getChatName());
        map.put("userId", chat.getUserId());
        namedParameterJdbcTemplate.update(SQL_SAVE, map);
    }

    @Override
    public void update(Chat chat) {
        Map map = new HashMap();
        map.put("chatName", chat.getChatName());
        map.put("chatId", chat.getChatName());
        namedParameterJdbcTemplate.update(SQL_UPDATE_NAME, map);
    }

    @Override
    public Integer findByToken(String token) {
        Map map = new HashMap<>();
        map.put("token", token);
        Integer userId = namedParameterJdbcTemplate.update(SQL_FIND_USER_BY_TOKEN, map);
        return userId;
    }
}
