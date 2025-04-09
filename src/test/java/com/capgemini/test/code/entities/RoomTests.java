package com.capgemini.test.code.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.capgemini.test.code.domain.entities.Person;
import com.capgemini.test.code.domain.entities.Room;
import com.capgemini.test.code.domain.entities.roles.Admin;

public class RoomTests {

    @Nested
    @DisplayName("User_Room tests")
    class UserRoomTests {
        Person p = new Person("Nombre", "tengo@y.ole",
                new Admin(), "000000000", "12345678A");
        Room r = new Room("Room1");

        @Test
        @DisplayName("Test add user")
        public void addUserRoom() {
            r.addPerson(p);
            assertEquals(r, p.getRoom());
            assertTrue(r.getPersons().contains(p));
        }

        @Test
        @DisplayName("Test remove user")
        public void removeUserRoom() {
            r.removePerson(p);
            assertEquals(null, p.getRoom());
            assertFalse(r.getPersons().contains(p));
        }

    }

}
