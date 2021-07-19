package com.maltsevve.springBootApp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
