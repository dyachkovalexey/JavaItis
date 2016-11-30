package ru.itis.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.itis.dtos.UserDto;

public class UserRestClient {

    private RestTemplate restTemplate;

    public UserRestClient() {
        restTemplate = new RestTemplate();
    }

    public void showAll(UserDto userDto) {
        restTemplate.postForObject("http://localhost:8081/users", userDto, UserDto.class);
    }

    public UserDto[] registration() {
        ResponseEntity<UserDto[]> responseEntity = restTemplate.getForEntity("http://localhost:8081/users", UserDto[].class);
        UserDto[] userDtos = responseEntity.getBody();

        return userDtos;
    }
}
