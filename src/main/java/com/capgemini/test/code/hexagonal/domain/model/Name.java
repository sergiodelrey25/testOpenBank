package com.capgemini.test.code.hexagonal.domain.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class Name {
    @Size(max = 6, message = "El nombre no puede ser mayor de 6 caracteres")
    private String value;

    public Name(String value) {
        this.value = value;
    }
}
