package com.capgemini.test.code.domain.contracts.repositories;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.test.code.domain.entities.Room;
import com.capgemini.test.code.exceptions.NotFoundException;

public interface RoomRepository extends CrudRepository<Room, Long> {
    default Room update(Room item) throws NotFoundException {
        if (!existsById(item.getId()))
            throw new NotFoundException();
        return save(item);
    }
}
