package ru.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itis.dto.UsersDto;
import ru.itis.models.Users;
import ru.itis.service.UsersService;


@RestController
public class UsersController {

    @Autowired
    UsersService usersService;


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void registration(@RequestBody Object[] user) {
        String name = user[0].toString();
        String login = user[1].toString();
        Integer password = Integer.parseInt(user[2].toString());
        String token = usersService.registrationNewUser(name, login, password);
        //return token;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void auth(@RequestHeader String login, String password) {
        String token = usersService.checkUser(login, password.hashCode());
        //(@ResponseBody String token);
    }
}
