package com.example.simpleapi.controllers.usercontroller;

import com.example.simpleapi.entities.User.User;
import com.example.simpleapi.entities.User.UserRole;
import com.example.simpleapi.services.userService.LoginRequest;
import com.example.simpleapi.services.userService.LoginResponse;
import com.example.simpleapi.services.userService.RegisterRequest;
import com.example.simpleapi.services.userService.UserServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@CrossOrigin(origins = {"http://127.0.0.1:5500/"})
@RequestMapping("api/v1/account")
public class UserController {
    private final UserServiceImplementation userServiceImplementation;

    @PostMapping("/register")
    public void register (@RequestBody RegisterRequest request, HttpServletResponse response) {

        UserRole role = switch (request.getRole()) {
            case "ADMIN_ROLE" -> UserRole.ADMIN_ROLE;
            case "BUYER_ROLE" -> UserRole.BUYER_ROLE;
            case "SELLER_ROLE" -> UserRole.SELLER_ROLE;
            case "CUSTOMER" -> UserRole.CUSTOMER;
            default -> UserRole.USER_ROLE;
        };

        userServiceImplementation.register(
                User.builder()
                        .firstname(request.getFirstname())
                        .lastname(request.getLastname())
                        .username(request.getUsername())
                        .password(request.getPassword())
                        .email(request.getEmail())
                        .role(role)
                        .mobile(request.getMobile())
                .build(),
                response
        );
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest request) {

        LoginResponse  response = userServiceImplementation.login(request);

        if (response.error() != null)
            return ResponseEntity.status(401).body(response);

        return  ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh (HttpServletRequest request, HttpServletResponse response) {


        LoginResponse  response1 = userServiceImplementation.refreshToken(
                request.getHeader("Authorization"), response);

        if (response1.error() != null)
            return ResponseEntity.status(401).body(response1);

        return  ResponseEntity.ok(response1);
    }


    @GetMapping
    public void getCustomers(HttpServletResponse response) {
//        userServiceImplementation.getCustomers(response);
        userServiceImplementation.findAllCustomers(response);
    }


    @GetMapping("{userId}")
    public void getCustomerById(HttpServletResponse response, @PathVariable("userId") Integer userId) {
        userServiceImplementation.getCustomer(response, userId);
    }

    @GetMapping("search")
    public List<User> searchCustomerByName(@RequestParam("pattern") String pattern) {
       return userServiceImplementation.searchCustomer(pattern);
    }
}
