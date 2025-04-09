package com.capgemini.test.code.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.checkerframework.checker.units.qual.N;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.capgemini.test.code.domain.entities.Person;
import com.capgemini.test.code.domain.entities.roles.Admin;

public class UserTests {
    private static final String INVALID_NAME_MSG = "El nombre no puede ser mayor de 6 caracteres.";
    private static final String INVALID_EMAIL_MSG = "El email no es válido.";

    private void checkInvalid(Person a, String message) {
        assertEquals(false, a.isValid());
        assertTrue(a.getErrorsMessage().contains(message));

    }

    @Nested
    @DisplayName("User name tests")
    class UserNameTests {
        @Test
        @DisplayName("Test user name longer than 6 characters")
        public void longerThan6Name() {
            Person p = new Person("NombreDemasiadoLargo", "email@normal.com",
                    new Admin(), "000000000", "12345678A");
            checkInvalid(p, INVALID_NAME_MSG);
        }

        @Test
        @DisplayName("Test user name equal to 6 characters")
        public void equalTo6Name() {
            Person p = new Person("Justo6", "email@normal.com",
                    new Admin(), "000000000", "12345678A");
            assertTrue(p.isValid());
        }

        @Test
        @DisplayName("Test user name shorter than 6 characters")
        public void shorterThan6Name() {
            Person p = new Person("Son4", "email@normal.com",
                    new Admin(), "000000000", "12345678A");
            assertTrue(p.isValid());
        }

    }

    @Nested
    @DisplayName("User email tests")
    class EmailUserTests {
        @Test
        @DisplayName("Test user email without '.' and '@'")
        public void withoutDotAndAt() {
            Person p = new Person("Nombre", "notengoniarrobanipunto",
                    new Admin(), "000000000", "12345678A");
            checkInvalid(p, INVALID_EMAIL_MSG);
        }

        @Test
        @DisplayName("Test user email without '.' but with '@'")
        public void withoutDotWithAt() {
            Person p = new Person("Nombre", "notengo@punto",
                    new Admin(), "000000000", "12345678A");
            checkInvalid(p, INVALID_EMAIL_MSG);
        }

        @Test
        @DisplayName("Test user email without '@' but with '.'")
        public void withoutAtWithDot() {
            Person p = new Person("Nombre", "notengo.arroba",
                    new Admin(), "000000000", "12345678A");
            checkInvalid(p, INVALID_EMAIL_MSG);
        }

        @Test
        @DisplayName("Test user email with '.' and '@'")
        public void withDotAndAt() {
            Person p = new Person("Nombre", "tengo@y.ole",
                    new Admin(), "000000000", "12345678A");
            assertTrue(p.isValid());
        }

    }

}
