package com.maltsevve.springBootApp.rest.users;

import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/register")
public class UserAccountRegistrationRestControllerV1 {
    private final UserService userService;

    @Autowired
    public UserAccountRegistrationRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.register(user);

        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }
}
