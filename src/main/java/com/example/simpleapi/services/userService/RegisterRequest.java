package com.example.simpleapi.services.userService;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private String role;
    private String mobile;

}
