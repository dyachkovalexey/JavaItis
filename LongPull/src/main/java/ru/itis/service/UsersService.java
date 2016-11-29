package ru.itis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dao.UsersDao;
import ru.itis.models.Users;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersDao usersDao;
    private SecureRandom random = new SecureRandom();

    public UsersService() {
    }

    public String nextSessionId() {
        return new BigInteger(100, random).toString(32);
    }

    public String registrationNewUser(String userName, String login, int password) {
        List<Users> usersList = usersDao.findAll();
        if (usersList.contains(login)==true) {
            return "error 403";
        } else {
            Users newUser = new Users(userName, login, password);
            usersDao.save(newUser);
            String token = nextSessionId();
            usersDao.addToken(newUser, token);
            return token;
        }
    }

    public String checkUser(String login, int password) {
        List<Users> usersList = usersDao.findAll();
        if (usersList.contains(login)==true) {
            Users user = usersDao.findByLogin(login);
            if (user.getUserHashPassword()==password) {
                String token = nextSessionId();
                usersDao.update(user, token);
                return token;
                //TODO: 200
            } else {
                //TODO: error 401
                return null;
            }
        } else {
            //TODO: error 401
            return null;
        }
    }
}
