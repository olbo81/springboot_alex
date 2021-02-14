package com.spring.springboot.springboot_alex.service;

import com.spring.springboot.springboot_alex.model.Role;
import com.spring.springboot.springboot_alex.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role findByRoleName(String roleName) {
        return repository.findByRole(roleName);

    }


}
