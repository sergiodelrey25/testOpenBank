package com.capgemini.test.code.hexagonal.usecase.find;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.capgemini.test.code.hexagonal.domain.model.User;
import com.capgemini.test.code.hexagonal.domain.repository.RoomRepositoryPort;

@Service
public class FindUserInRoomInteractor implements IFindUserInRoomInteractor {

    private final RoomRepositoryPort roomRepositoryAdapter;

    public FindUserInRoomInteractor(RoomRepositoryPort roomRepositoryAdapter) {
        this.roomRepositoryAdapter = roomRepositoryAdapter;
    }

    @Override
    public Optional<FindUserOutput> findUserInRoom(Long roomId, Long userId) {
        return mapToOutput((roomRepositoryAdapter.getUserInRoom(roomId, userId)));
    }

    private Optional<FindUserOutput> mapToOutput(Optional<User> optionalUser) {
        if (optionalUser.isEmpty()) {
            return Optional.empty();
        }
        User userInRoom = optionalUser.get();
        FindUserOutput output = new FindUserOutput();
        output.setId(userInRoom.getId().getValue());
        output.setName(userInRoom.getName().getValue());
        output.setEmail(userInRoom.getEmail().getValue());
        output.setDni(userInRoom.getDni().getValue());
        output.setPhone(userInRoom.getPhone().getValue());
        output.setRole(userInRoom.getRole().getClass().getSimpleName());
        return Optional.ofNullable(output);
    }

}
