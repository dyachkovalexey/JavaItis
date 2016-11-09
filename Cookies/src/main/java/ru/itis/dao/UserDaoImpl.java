package ru.itis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import ru.itis.models.Users;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.utils.UsersMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
@Service
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_GET_ALL = "SELECT * FROM users";
    //language=SQL
    public static final String SQL_REGISTRATION = "INSERT INTO users (user_name, user_login, user_password, user_token) VALUES " +
            "(:userName, :userLogin, :userPassword, :userToken)";
    //language=SQL
    public static final String SQL_FIND = "SELECT * FROM users WHERE user_login=:userLogin";
    //language=SQL
    public static final String SQL_FIND_BY_TOKEN = "SELECT * FROM users WHERE user_token=:userToken";
    //language=SQL
    private static final String SQL_UPDATE_USERS = "UPDATE users SET user_token = :userToken WHERE user_id=:userId;";

    @Autowired
    public UserDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public UserDaoImpl() {
    }

    public List<Users> getAll() {
        List<Users> users = (List<Users>)namedParameterJdbcTemplate.query(SQL_GET_ALL, new UsersMapper());
        return users;
    }

    public void update(String token, int id) {
        Map map = new HashMap();
        map.put("userToken", token);
        map.put("userId", id);
        namedParameterJdbcTemplate.update(SQL_UPDATE_USERS, map);
    }


    public void registration(Users users) {
        Map map = new HashMap();
        map.put("userName", users.getUserName());
        map.put("userLogin", users.getUserLogin());
        map.put("userPassword", users.getUserPassword());
        map.put("userToken", "");
        System.out.println(map.toString());
        namedParameterJdbcTemplate.update(SQL_REGISTRATION, map);
    }

    public Users find(String login) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userLogin", login);
        Users users = (Users)namedParameterJdbcTemplate.queryForObject(SQL_FIND, sqlParameterSource, new UsersMapper());
        return users;

    }

    public Users findByToken(String token) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userToken", token);
        Users users = (Users)namedParameterJdbcTemplate.queryForObject(SQL_FIND_BY_TOKEN, sqlParameterSource, new UsersMapper());
        return users;
    }
}
