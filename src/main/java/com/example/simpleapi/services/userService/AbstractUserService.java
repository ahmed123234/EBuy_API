package com.example.simpleapi.services.userService;

import com.example.simpleapi.entities.User.User;
import jakarta.servlet.http.HttpServletResponse;

public abstract class  AbstractUserService implements UserService {

    public abstract void register(User user, HttpServletResponse response);

//    public abstract void getCustomers(HttpServletResponse response);
}
