package com.capgemini.test.code.hexagonal.usecase.find;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserOutput {
    Long id;
    String name;
    String email;
    String dni;
    String phone;
    String role;
}
