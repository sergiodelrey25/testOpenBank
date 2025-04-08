package com.capgemini.test.code.domain.entities.roles;

import com.capgemini.test.code.domain.entities.Person;

public class Admin implements Role{

    private final String name = "Admin";
    //Simulación de un correo electrónico
    

    @Override
    public String getNotification(Person to) {
        return """
    From: System
    To: """ + to.getEmail() + """
    \tSubject: Notification
    \tMessage: Usuario guardado.
    \tPlease check your inbox for more details.""";
    }

    @Override
    public String getName() {
        return this.name;
    }

}
