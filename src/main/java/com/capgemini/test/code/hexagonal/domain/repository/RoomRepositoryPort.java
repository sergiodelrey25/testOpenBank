package com.capgemini.test.code.hexagonal.domain.repository;

import java.util.Optional;

import com.capgemini.test.code.hexagonal.domain.model.User;

public interface RoomRepositoryPort {
    Optional<User> getUserInRoom(Long roomId, Long userId);

    void saveUserInRoom(String roomId, User user);
}
