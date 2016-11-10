package ru.itis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Verifier {

    //language=SQL
    private static final String SQL_FIND_TOKEN = "SELECT * FROM users WHERE token = :token";


    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public Verifier(DriverManagerDataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public static void verifyUserExistByToken(String token) {
        Map<String, String> namedParameters = new HashMap<String, String>();
        namedParameters.put("token", token);
        if(namedParameterJdbcTemplate.queryForList(SQL_FIND_TOKEN, namedParameters).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
