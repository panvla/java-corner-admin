package com.vladimirpandurov.javacorneradmin.dao;

import com.vladimirpandurov.javacorneradmin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
