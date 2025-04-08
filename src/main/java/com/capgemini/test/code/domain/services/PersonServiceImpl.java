package com.capgemini.test.code.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.test.code.domain.contracts.repositories.PersonRepository;
import com.capgemini.test.code.domain.contracts.services.PersonService;
import com.capgemini.test.code.domain.entities.Person;
import com.capgemini.test.code.exceptions.DuplicateKeyException;
import com.capgemini.test.code.exceptions.InvalidDataException;
import com.capgemini.test.code.exceptions.NotFoundException;

@Service
public class PersonServiceImpl implements PersonService {
    private PersonRepository repo;

    public PersonServiceImpl(PersonRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Person> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<Person> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public Person add(Person item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("Item cannot be null");
        }
        if (item.isInvalid()) {
            throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
        }
        return repo.insert(item);
    }

    @Override
    public Person modify(Person item) throws NotFoundException, InvalidDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modify'");
    }

    @Override
    public void delete(Person item) throws InvalidDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
