package com.maltsevve.springBootApp.rest.users;

import com.maltsevve.springBootApp.dto.UserDto;
import com.maltsevve.springBootApp.model.User;
import com.maltsevve.springBootApp.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/register")
@RequiredArgsConstructor
@Api(tags = { "Users: account registration" })
public class UserAccountRegistrationRestControllerV1 {
    private final UserService userService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> saveUser(@RequestBody @Valid User user) {
        HttpHeaders headers = new HttpHeaders();

        if (Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.register(user);

        return new ResponseEntity<>(UserDto.fromUser(user), headers, HttpStatus.CREATED);
    }
}
