package com.capgemini.test.code.hexagonal.infrastructure.controller;

import com.capgemini.test.code.hexagonal.domain.model.User;

public class UserDisplayMapper {
    public static UserDisplay fromDomainToDto(User domain) {
        return new UserDisplay(
                domain.getId().getValue(),
                domain.getName().getValue(),
                domain.getEmail().getValue(),
                domain.getPhone().getValue(),
                domain.getRole().getName(),
                domain.getDni().getValue());
    }

}
