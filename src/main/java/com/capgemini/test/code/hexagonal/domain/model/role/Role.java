package com.capgemini.test.code.hexagonal.domain.model.role;

import com.capgemini.test.code.hexagonal.domain.model.User;

public interface Role {

    Notification getNotification(User to);

    String getName();
}