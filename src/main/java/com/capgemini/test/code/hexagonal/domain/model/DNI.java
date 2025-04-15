package com.capgemini.test.code.hexagonal.domain.model;

import lombok.Getter;

@Getter
public class DNI {
    private String value;

    public DNI(String value) {
        this.value = value;
    }
}
