package com.capgemini.test.code.hexagonal.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Room {

    private Name name;
    private List<User> users = new ArrayList<>();
}
