package com.capgemini.test.code.domain.contracts.repositories;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.test.code.domain.entities.Room;

public interface RoomRepository extends CrudRepository<Room, Long> {
}
