package com.vladimirpandurov.javacorneradmin.service;

import com.vladimirpandurov.javacorneradmin.entity.User;

public interface UserService {

    User loadUserByEmail(String email);

    User createUser(String email, String password);

    void assignRoleToUser(String email, String roleName);
}
