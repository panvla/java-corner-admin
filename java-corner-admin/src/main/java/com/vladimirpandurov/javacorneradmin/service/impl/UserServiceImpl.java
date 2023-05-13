package com.vladimirpandurov.javacorneradmin.service.impl;

import com.vladimirpandurov.javacorneradmin.dao.RoleDao;
import com.vladimirpandurov.javacorneradmin.dao.UserDao;
import com.vladimirpandurov.javacorneradmin.entity.Role;
import com.vladimirpandurov.javacorneradmin.entity.User;
import com.vladimirpandurov.javacorneradmin.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private RoleDao roleDao;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        String encodedPasword = passwordEncoder.encode(password);
        return userDao.save(new User(email, encodedPasword));
    }

    @Override
    public void assignRoleToUser(String email, String roleName) {
        User user = loadUserByEmail(email);
        Role role = roleDao.findByName(roleName);
        user.assignRoleToUser(role);
    }
}
