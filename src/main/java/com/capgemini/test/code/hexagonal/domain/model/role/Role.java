package com.capgemini.test.code.hexagonal.domain.model.role;

import com.capgemini.test.code.domain.entities.Person;

public interface Role {

    Notification getNotification(Person to);

    String getName();
}