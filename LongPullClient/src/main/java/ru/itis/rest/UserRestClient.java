package ru.itis.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.itis.dtos.UserDto;
import ru.itis.models.User;

import java.util.List;

public class UserRestClient {

    private RestTemplate restTemplate;

    public UserRestClient() {
        restTemplate = new RestTemplate();
    }

    public void showAll(UserDto userDto) {
        restTemplate.postForObject("http://localhost:8081/users", userDto, UserDto.class);
    }


    public void registration(String name,String login,Integer password) {
        //restTemplate.postForObject("http://localhost:8081/users", user, User.class);
        User user = new User(name, login, password);
        Object[] list = {name, login, password};
        restTemplate.postForObject("http://localhost:8081/users", list, Object[].class);
    }
}
