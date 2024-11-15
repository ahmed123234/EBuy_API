package com.example.simpleapi.services.userService;

import com.example.simpleapi.Repositories.UserRepository;
import com.example.simpleapi.entities.User.User;
import com.example.simpleapi.entities.User.UserRole;
import com.example.simpleapi.responsehandeling.Response;
import com.example.simpleapi.security.JwtService;
import com.example.simpleapi.security.PasswordEncoder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public  class UserServiceImplementation extends AbstractUserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final String USERNAME_NOT_FOUND =  "user with username %s not found";


    @Override
    public void register(User user, HttpServletResponse response) {


        boolean isValidUsername = Response.isAvailableUser(
                response,
                userRepository.findByUsername(user.getUsername()),
                "user with username %s is already found",
                user.getUsername()
                );


        boolean isValidEmail = Response.isAvailableUser(
                response,
                userRepository.findByEmail(user.getEmail()),
                "user with email %s is already found",
                user.getUsername()
        );

        if (isValidUsername && isValidEmail) {
            log.info("username {} is unique and seems good", user.getUsername());
            log.info("email {} is unique and seems good", user.getEmail());

            user.setPassword(passwordEncoder.bCryptPasswordEncoder()
                    .encode(user.getPassword()));

//            enable the user after verify their account
            user.setEnabled(true);

//            set register date
            user.setJoinDate(new Date());

            userRepository.save(user);
            Map <String, User> body = new HashMap<>();
            body.put("message", userRepository.findByUsername(user.getUsername()).get());
            Response.setResponse(response, 201, body);
        } else {
            log.error("username {} is already taken" , user.getUsername());
            log.error("email {} is already taken" , user.getEmail());
        }



//        user.setRole(UserRole.USER_ROLE);


    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {


        UserDetails userDetails = loadUserByUsername(loginRequest.getUsername());

//        String token = "";
        LoginResponse response;

        if(userDetails != null) {
//            checkPassword(userDetails.getPassword(), loginRequest.getPassword());

            if(passwordEncoder.bCryptPasswordEncoder().matches(loginRequest.getPassword(), userDetails.getPassword())) {
                Map<String, String> tokens = jwtService.generateToken(userDetails);
                response = LoginResponse.builder().accessToken(tokens
                        .get("access token")).refreshToken(tokens.get("refresh token")).build();
//                token = jwtService.generateToken(userDetails);
                log.info("user credentials {} , {} are OK", loginRequest.getUsername(), loginRequest.getPassword());
            }   else  {
                response = LoginResponse.builder().error("the user credentials is not correct").build();
                log.error("the user credentials {} is not correct", loginRequest.getPassword());
            }
        } else {
//            token = "the user not authenticated yet";
           response = LoginResponse.builder().error("No such user").build();
        }

        return response;
    }

    @Override
    public Boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    @Override
    public Boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    @Override
    public Boolean checkPassword(String username, String password) {
        return false;
    }

    @Override
    public void getCustomers(HttpServletResponse response) {
        Response.setResponse(response, 200, userRepository.findAll());
    }

    @Override
    public List<User> getCustomers() {
        return userRepository.findAll();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("inside loadUserByUsername");
        return userRepository.findByUsername(username).
                orElseThrow(() ->{
               throw new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND, username));

        });

    }

    public LoginResponse refreshToken(String authorization, HttpServletResponse response) {

        Map<String, String> tokens = new HashMap<>();
        if(authorization != null && authorization.startsWith("Bearer ")) {
            String username = jwtService.extractUsername(authorization.substring(7));

            tokens = jwtService.generateToken(loadUserByUsername(username));
            log.info("inside refresh token service, username = {}", username);
        }
        return LoginResponse.builder().accessToken(tokens.get("access token"))
                .refreshToken(tokens.get("refresh token")).build();
    }

    public void getCustomer(HttpServletResponse response, Integer userId) {
        Response.checkObjectAvailability(response, userRepository.findById(userId),
                "No such user with Id %s", userId);
    }

    public void findAllCustomers(HttpServletResponse response) {
        Response.setResponse(response, 200, userRepository.findAllByRole(UserRole.CUSTOMER));
    }

    public List<User> searchCustomer(String pattern) {
        return userRepository.searchCustomer(pattern);
//        Response.setResponse(response, 200,
//                userRepository.searchCustomer(pattern));
    }
}
