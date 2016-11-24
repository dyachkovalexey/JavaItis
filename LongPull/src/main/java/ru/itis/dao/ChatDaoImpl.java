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
import java.util.List;

@Repository
@Service
public class ChatDaoImpl implements  ChatDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_FIND_ALL = "SELECT * FROM chatsdb";
    //language=SQL
    public static final String SQL_SAVE = "INSERT  INTO chatsdb (chat_name) VALUES (:chatName)";
    //language=SQL
    public static final String SQL_FIND = "SELECT * FROM chatsdb WHERE chat_id=:chatId";

    @Autowired
    public ChatDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public ChatDaoImpl() {
    }

    @Override
    public List<Chat> findAll() {
        List chat = namedParameterJdbcTemplate.query(SQL_FIND_ALL, new ChatMapper());
        return chat;
    }

    @Override
    public Chat find(int chatId) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("chatId", chatId);
        Chat chat = (Chat)namedParameterJdbcTemplate.queryForObject(SQL_FIND, sqlParameterSource, new ChatMapper());
        return chat;
    }

    @Override
    public int save(Chat chatDto) {
        return 0;
    }

    @Override
    public void update(Chat chatDto) {

    }
}
