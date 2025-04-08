package com.capgemini.test.code.domain.entities.roles;

import com.capgemini.test.code.domain.entities.Person;

public class SuperAdmin implements Role{
    private final String name = "SuperAdmin";
    //Simulación de un SMS

    @Override
    public String getNotification(Person to) {
        return 
        to.getName() +"""
         you have a NEW Message from System:
        Usuario guardado.
        """;
    }
    @Override
    public String getName() {
        return this.name;
    }
}
