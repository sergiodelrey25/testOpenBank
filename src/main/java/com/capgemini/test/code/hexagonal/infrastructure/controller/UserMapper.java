package com.capgemini.test.code.hexagonal.infrastructure.controller;

import com.capgemini.test.code.hexagonal.usecase.create.CreateUserInput;
import com.capgemini.test.code.hexagonal.usecase.find.FindUserOutput;

public class UserMapper {

    public static UserDisplay toUserDisplay(FindUserOutput user) {
        return new UserDisplay(user.getId());
    }

    public static UserDisplay toUserDisplay(Long id) {
        return new UserDisplay(id);
    }

    public static CreateUserInput toCreateUserInput(UserCreate user) {
        return new CreateUserInput(user.getName(), user.getEmail(), user.getDni(), user.getPhone(), user.getRole());
    }

    public static UserDetails toUserDetails(FindUserOutput user) {
        return new UserDetails(
                user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getRole(), user.getDni());
    }

}
