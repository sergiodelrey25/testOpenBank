package com.capgemini.test.code.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.test.code.domain.contracts.repositories.RoomRepository;
import com.capgemini.test.code.domain.contracts.services.RoomService;
import com.capgemini.test.code.domain.entities.Room;
import com.capgemini.test.code.exceptions.DuplicateKeyException;
import com.capgemini.test.code.exceptions.InvalidDataException;
import com.capgemini.test.code.exceptions.NotFoundException;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepository repo;

    public RoomServiceImpl(RoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Room> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<Room> getOne(Long id) {
        return repo.findById(id);
    }

    @Override
    public Room add(Room item) throws DuplicateKeyException, InvalidDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public Room modify(Room item) throws NotFoundException, InvalidDataException {
        if (item == null)
            throw new InvalidDataException("No puede ser nulo");
        if (item.isInvalid())
            throw new InvalidDataException(item.getErrorsMessage(), item.getErrorsFields());
        return repo.update(item);
    }

    @Override
    public void delete(Room item) throws InvalidDataException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

}
