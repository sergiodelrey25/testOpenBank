package com.capgemini.test.code.hexagonal.domain.model;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class Email {

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El email no es válido")
    private String value;

    public Email(String value) {
        this.value = value;
    }
}
