package ru.itis.dao;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import ru.itis.models.Autos;
import ru.itis.utils.AutoMapper;

import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lo0ny on 21.10.2016.
 */
public class AutoDaoImpl implements  AutoDao {

    private Connection connection;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_GET_ALL = "SELECT * FROM auto";
    //language=SQL
    public static final String SQL_ADD = "INSERT INTO auto (auto_name, auto_number, user_id) VALUES " +
            "(:autoName, :autoNumber, :userId)";
    //language=SQL
    public static final String SQL_FIND = "SELECT * FROM auto WHERE auto_id=:autoId";
    //language=SQL
    public static final String SQL_GET_ALL_BY_USERID = "SELECT * FROM auto WHERE user_id=:userId";

    public AutoDaoImpl() {

    }

    public AutoDaoImpl(DataSource dataSource, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        try {
            this.connection = dataSource.getConnection();
            this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Autos> getAll() {
        List autos = namedParameterJdbcTemplate.query(SQL_GET_ALL, new AutoMapper());
        return autos;
    }



    public void add(Autos autos) {
        Map map = new HashMap();
        map.put("autoName", autos.getAutoName());
        map.put("autoNumber", autos.getAutoNumber());
        map.put("userId", autos.getUserId());
        namedParameterJdbcTemplate.update(SQL_ADD, map);
    }

    public Autos find(int id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("autoId", id);
        Autos autos = (Autos)namedParameterJdbcTemplate.queryForObject(SQL_FIND, sqlParameterSource, new AutoMapper());
        return autos;
    }

    public List<Autos> getAllByUserId(int userId){
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("userId", userId);
        List autos = namedParameterJdbcTemplate.query(SQL_GET_ALL_BY_USERID, sqlParameterSource, new AutoMapper());
        return autos;
    }
}