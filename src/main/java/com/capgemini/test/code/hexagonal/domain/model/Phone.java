package com.capgemini.test.code.hexagonal.domain.model;

import lombok.Getter;

@Getter
public class Phone {
    private String value;

    public Phone(String value) {
        this.value = value;
    }

}
