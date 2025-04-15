package com.capgemini.test.code.hexagonal.domain.model.role;

import com.capgemini.test.code.hexagonal.domain.model.User;

public class SuperAdmin implements Role {
    private final String name = "ADMIN";
    private final String url = "http://localhost:1080/sms";
    // Simulación de un SMS

    @Override
    public Notification getNotification(User to) {
        String phone = to.getPhone().getValue();
        String message = "Usuario guardado.";
        String jsonInputString = String.format("{\"phone\": \"%s\", \"message\": \"%s\"}", phone, message);
        return new Notification(url, jsonInputString);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
