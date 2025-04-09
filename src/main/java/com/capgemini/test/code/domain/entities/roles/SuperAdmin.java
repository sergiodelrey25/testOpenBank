package com.capgemini.test.code.domain.entities.roles;

import com.capgemini.test.code.domain.entities.Person;

public class SuperAdmin implements Role {
    private final String name = "ADMIN";
    private final String url = "http://localhost:1080/sms";
    // Simulación de un SMS

    @Override
    public Notification getNotification(Person to) {
        String phone = to.getPhone();
        String message = "Usuario guardado.";
        String jsonInputString = String.format("{\"phone\": \"%s\", \"message\": \"%s\"}", phone, message);
        return new Notification(url, jsonInputString);
    }

    @Override
    public String getName() {
        return this.name;
    }
}
