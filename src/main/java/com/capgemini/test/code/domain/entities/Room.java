package com.capgemini.test.code.domain.entities;

import java.util.List;

import com.capgemini.test.code.domain.core.entities.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room extends AbstractEntity<Room> {
    private String name;
    @OneToMany(mappedBy = "room")
    private List<Person> persons;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room() {
        super();
    }

    public Room(String name) {
        super();
        this.name = name;
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
        person.setRoom(this);
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
        person.setRoom(null);
    }

}
