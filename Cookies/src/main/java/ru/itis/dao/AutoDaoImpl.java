package ru.itis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.itis.models.Autos;
import ru.itis.utils.AutoMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Service
public class AutoDaoImpl implements  AutoDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //language=SQL
    public static final String SQL_GET_ALL = "SELECT * FROM auto";
    //language=SQL
    public static final String SQL_ADD = "INSERT INTO auto (auto_name, auto_number, user_id) VALUES " +
            "(:autoName, :autoNumber, :userId)";
    //language=SQL
    public static final String SQL_FIND = "SELECT * FROM auto WHERE auto_id=?";

    public AutoDaoImpl() {

    }

    @Autowired
    public AutoDaoImpl(DataSource dataSource) {
            this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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
}