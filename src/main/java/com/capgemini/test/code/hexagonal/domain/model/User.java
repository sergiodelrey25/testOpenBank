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

    public User(Email email, Name name, DNI dni, Phone phone, Role role) {
        this.email = email;
        this.name = name;
        this.dni = dni;
        this.phone = phone;
        this.role = role;
    }
}
