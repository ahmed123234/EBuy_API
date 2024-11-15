package com.example.simpleapi.services.userService;

import com.example.simpleapi.entities.User.User;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void register(User user, HttpServletResponse response);

    LoginResponse login (LoginRequest loginRequest);

    Boolean checkUsername(String username);
    Boolean checkEmail(String email);

    Boolean checkPassword(String username, String password);

    void getCustomers(HttpServletResponse response);
    List<User> getCustomers();
}
