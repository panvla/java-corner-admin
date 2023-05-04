package com.vladimirpandurov.javacorneradmin.service.impl;

import com.vladimirpandurov.javacorneradmin.dao.RoleDao;
import com.vladimirpandurov.javacorneradmin.entity.Role;
import com.vladimirpandurov.javacorneradmin.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(String roleName) {
        return roleDao.save(new Role(roleName));
    }
}
