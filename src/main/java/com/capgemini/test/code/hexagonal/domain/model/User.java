package com.capgemini.test.code.hexagonal.domain.model;

import com.capgemini.test.code.hexagonal.domain.model.role.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private ID id;
    private Email email;
    private Name name;
    private DNI dni;
    private Phone phone;
    private Role role;
}
