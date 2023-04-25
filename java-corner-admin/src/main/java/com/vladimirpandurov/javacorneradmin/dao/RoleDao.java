package com.vladimirpandurov.javacorneradmin.dao;

import com.vladimirpandurov.javacorneradmin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
