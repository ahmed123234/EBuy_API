package com.example.simpleapi.controllers.usercontroller;

import com.example.simpleapi.entities.User.User;
import com.example.simpleapi.services.userService.UserServiceImplementation;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = {"http://127.0.0.1:5500"},
//        allowedHeaders = {"Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method",
//        "Access-Control-Request-Headers", "Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}
//)
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserServiceImplementation userService;
    //    Get ALl customers in the system
//    @CrossOrigin(origins = {"http://127.0.0.1:5500"},
//        exposedHeaders = {"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"}
//    )
//    @GetMapping
//    public void getCustomers(HttpServletResponse response) {
//        userService.getCustomers(response);
//    }


    @GetMapping("{userId}")
    public void getCustomerById(HttpServletResponse response, @PathVariable("userId") Integer userId) {
        userService.getCustomer(response, userId);
    }

    @GetMapping
    public ResponseEntity<List<User>> getCustomers() {
        return ResponseEntity.ok().body(userService.getCustomers());
    }
}
