package com.capgemini.test.code.hexagonal.usecase.create;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserInput {

    String name;
    String email;
    String dni;
    String phone;
    String role;
}
