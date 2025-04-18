package com.capgemini.test.code.hexagonal.infrastructure.db.postgres.adapter;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.capgemini.test.code.hexagonal.domain.model.User;
import com.capgemini.test.code.hexagonal.domain.repository.RoomRepositoryPort;
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
    public void saveUserInRoom(String roomId, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveUserInRoom'");
    }

}
