package ru.itis.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.models.Users;
import ru.itis.utils.UserMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Service
public class UsersDaoImpl implements  UsersDao{

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_FIND_ALL = "SELECT * FROM chat_user";
    //language=SQL
    public static final String SQL_FIND_BY_ID = "SELECT * FROM chat_user WHERE user_id=:userId";
    //language=SQL
    public static final String SQL_ADD_TO_DB = "INSERT INTO chat_user (user_name, user_login, user_hash_password) VALUES " +
            "(:userName, :userLogin, :userHashPassword)";
    //language=SQL
    public static final String SQL_UPDATE = "UPDATE chat_auth SET token=:token WHERE user_id=:userId";
    //language=SQL
    public static final String SQL_ADD_USER_FT_CHAT_TO_DB = "INSERT INTO user_ft_chat (user_id, chat_id) VALUES (:userId, :chatId)";
    //language=SQL
    public static final String SQL_ADD_TOKEN = "INSERT INTO chat_auth (user_id, token) VALUES (:userId, :token)";
    //language=SQL
    public static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM chat_user WHERE user_login=:login";

    @Autowired
    public UsersDaoImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public UsersDaoImpl() {
    }

    @Override
    public List<Users> findAll() {
        List<Users> users = (List<Users>)namedParameterJdbcTemplate.query(SQL_FIND_ALL, new UserMapper());
        return users;
    }

    @Override
    public Users find(Integer id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", id);
        Users users = (Users)namedParameterJdbcTemplate.queryForObject(SQL_FIND_BY_ID, sqlParameterSource, new UserMapper());
        return users;
    }

    @Override
    public void save(Users users) {
        Map map = new HashMap();
        map.put("userName", users.getUserName());
        map.put("userLogin", users.getUserLogin());
        map.put("userHashPassword", users.getUserHashPassword());
        namedParameterJdbcTemplate.update(SQL_ADD_TO_DB, map);
    }

    @Override
    public void update(Users users, String token) {
        Map map = new HashMap();
        map.put("token", token);
        map.put("userId", users.getUserId());
        namedParameterJdbcTemplate.update(SQL_UPDATE, map);
    }

    @Override
    public void saveUserToChat(Integer userId, Integer chatId) {
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("chatId", chatId);
        namedParameterJdbcTemplate.update(SQL_ADD_USER_FT_CHAT_TO_DB, map);
    }

    @Override
    public void addToken(Users user, String token) {
        Map map = new HashMap();
        map.put("userId", user.getUserId());
        map.put("token", token);
        namedParameterJdbcTemplate.update(SQL_ADD_TOKEN, map);
    }

    @Override
    public Users findByLogin(String login) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("login", login);
        Users users = (Users)namedParameterJdbcTemplate.queryForObject(SQL_FIND_USER_BY_LOGIN, sqlParameterSource, new UserMapper());
        return users;
    }
}
