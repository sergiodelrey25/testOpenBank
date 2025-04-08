package com.capgemini.test.code.domain.contracts;

import org.springframework.data.repository.Repository;

import com.capgemini.test.code.domain.entities.Person;

public interface PersonRepository extends Repository<Person, Long> {

}
