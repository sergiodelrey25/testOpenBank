package com.capgemini.test.code.domain.entities.roles;

import com.capgemini.test.code.domain.entities.Person;

public interface Role {
    void sendNotification(Person to);

    String getNotification(Person to);

    String getName();
}