package com.maltsevve.springBootApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maltsevve.springBootApp.model.Status;
import com.maltsevve.springBootApp.model.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private Status status;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setStatus(status);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setStatus(user.getStatus());
        return userDto;
    }

    public static List<UserDto> fromUsers(List<User> users){
        return users.stream().map(UserDto::fromUser).collect(Collectors.toList());
    }
}
