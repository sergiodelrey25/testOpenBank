package com.capgemini.test.code.domain.contracts.repositories;

import org.springframework.data.repository.CrudRepository;
import com.capgemini.test.code.domain.entities.Person;
import com.capgemini.test.code.exceptions.DuplicateKeyException;
import com.capgemini.test.code.exceptions.NotFoundException;

public interface PersonRepository extends CrudRepository<Person, Long> {
    default Person insert(Person item) throws DuplicateKeyException {
        if (existsById(item.getId()))
            throw new DuplicateKeyException();
        return save(item);
    }

    default Person update(Person item) throws NotFoundException {
        if (!existsById(item.getId()))
            throw new NotFoundException();
        return save(item);
    }

}
