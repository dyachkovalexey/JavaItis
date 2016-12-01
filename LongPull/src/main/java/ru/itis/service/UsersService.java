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
        Users newUser = new Users(userName, login, password);

        synchronized (newUser) {
            if (checkOnReg(login)==true) {
                return "error 403";
            } else {
                usersDao.save(newUser);
                String token = nextSessionId();
                // обновление юзера, для получения id
                Users newUserWithId = usersDao.findByLogin(newUser.getUserLogin());
                usersDao.addToken(newUserWithId, token);
                return token;
            }
        }
    }

    public boolean checkOnReg(String login) {
        try {
            Users user = usersDao.findByLogin(login);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public String checkUser(String login, int password) {
        synchronized (login) { //TODO:
            if (checkOnReg(login) == false) {
                Users user = usersDao.findByLogin(login);
                if (user.getUserHashPassword() == password) {
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
}
