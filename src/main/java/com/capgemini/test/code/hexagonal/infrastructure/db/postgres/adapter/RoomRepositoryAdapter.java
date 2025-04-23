package com.capgemini.test.code.hexagonal.infrastructure.db.postgres.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.capgemini.test.code.hexagonal.domain.model.User;
import com.capgemini.test.code.hexagonal.domain.repository.RoomRepositoryPort;
import com.capgemini.test.code.hexagonal.infrastructure.db.postgres.entity.RoomEntity;
import com.capgemini.test.code.hexagonal.infrastructure.db.postgres.entity.UserEntity;
import com.capgemini.test.code.hexagonal.infrastructure.db.postgres.mapper.UserMapper;
import com.capgemini.test.code.hexagonal.infrastructure.db.postgres.repository.RoomJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RoomRepositoryAdapter implements RoomRepositoryPort {

    private final RoomJpaRepository roomJpaRepository;

    @Override
    public Optional<User> getUserInRoom(Long roomId, Long userId) {
        Optional<UserEntity> userEntity = roomJpaRepository.findUserInRoom((roomId), (userId));
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            return Optional.ofNullable(UserMapper.fromEntityToDomain(user));
        }
        return Optional.empty();
    }

    @Override
    public Long saveUserInRoom(Long roomId, User user) {
        UserEntity userEntity = UserMapper.fromDomainToEntity(user);
        Optional<RoomEntity> r = roomJpaRepository.findById(roomId);
        if (r.isEmpty()) {
            throw new IllegalArgumentException("Room not found with id: " + roomId);
        }
        if (r.get().getUsers().contains(userEntity)) {
            throw new IllegalArgumentException("This user already exists in room with id: " + roomId);
        }
        r.get().addUser(userEntity);
        roomJpaRepository.save(r.get());
        return userEntity.getId();
    }

}
