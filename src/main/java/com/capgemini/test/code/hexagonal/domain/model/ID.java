package com.capgemini.test.code.hexagonal.domain.model;

import lombok.Getter;

@Getter
public class ID {
    private Long value;

    public ID(Long value) {
        this.value = value;
    }
}
