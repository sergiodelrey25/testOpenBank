package com.capgemini.test.code.hexagonal.usecase.create;

import lombok.Getter;

@Getter
public class CreateUserInput {

    Long id;
    String name;
    String email;
    String dni;
    String phone;
    String role;
}
