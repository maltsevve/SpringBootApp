package com.maltsevve.springBootApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.maltsevve.springBootApp.model.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModerUserDto {
    private Long id;
    private String username;
    private String email;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);

        return user;
    }

    public static ModerUserDto fromUser(User user) {
        ModerUserDto moderUserDto = new ModerUserDto();
        moderUserDto.setId(user.getId());
        moderUserDto.setUsername(user.getUsername());
        moderUserDto.setEmail(user.getEmail());

        return moderUserDto;
    }

    public static List<ModerUserDto> fromUsers(List<User> users){
        return users.stream().map(ModerUserDto::fromUser).collect(Collectors.toList());
    }
}
