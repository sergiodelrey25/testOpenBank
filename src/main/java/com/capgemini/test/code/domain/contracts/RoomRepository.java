package com.capgemini.test.code.domain.contracts;

import org.springframework.data.repository.Repository;

import com.capgemini.test.code.domain.entities.Room;

public interface RoomRepository extends Repository<Room, Long> {
}
