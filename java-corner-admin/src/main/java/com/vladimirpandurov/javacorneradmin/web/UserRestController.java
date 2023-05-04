package com.vladimirpandurov.javacorneradmin.web;

import com.vladimirpandurov.javacorneradmin.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    private UserService userService;

    @GetMapping("/users")
    public boolean checkIfEmailExists(@RequestParam(name = "email", defaultValue = "") String email){
        return userService.loadUserByEmail(email)!= null;
    }
}