package com.capgemini.test.code.domain.entities;

import java.util.List;

import com.capgemini.test.code.domain.core.entities.AbstractEntity;

import jakarta.persistence.OneToMany;

public class Room extends AbstractEntity<Room> {
    private String name;
    @OneToMany(mappedBy = "room_id")
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

}
