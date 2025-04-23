package com.capgemini.test.code.hexagonal.usecase.create;

import org.springframework.stereotype.Service;

import com.capgemini.test.code.hexagonal.domain.model.DNI;
import com.capgemini.test.code.hexagonal.domain.model.Email;
import com.capgemini.test.code.hexagonal.domain.model.Name;
import com.capgemini.test.code.hexagonal.domain.model.Phone;
import com.capgemini.test.code.hexagonal.domain.model.User;
import com.capgemini.test.code.hexagonal.domain.model.role.Admin;
import com.capgemini.test.code.hexagonal.domain.model.role.Role;
import com.capgemini.test.code.hexagonal.domain.model.role.SuperAdmin;
import com.capgemini.test.code.hexagonal.domain.repository.RoomRepositoryPort;

@Service
public class CreateUserInRoomInteractor implements ICreateUserInRoomInteractor {
    private final RoomRepositoryPort roomRepositoryAdapter;

    public CreateUserInRoomInteractor(RoomRepositoryPort roomRepositoryAdapter) {
        this.roomRepositoryAdapter = roomRepositoryAdapter;
    }

    @Override
    public Long createUserInRoom(Long roomId, CreateUserInput user) {
        return roomRepositoryAdapter.saveUserInRoom(roomId, mapToUser(user));
    }

    private User mapToUser(CreateUserInput user) {
        Role role = mapToRole(user.getRole());
        return new User(
                new Email(user.getEmail()),
                new Name(user.getName()),
                new DNI(user.getDni()),
                new Phone(user.getPhone()),
                role);
    }

    private Role mapToRole(String role) {
        switch (role.toUpperCase()) {
            case "USER":
                return new Admin();
            case "ADMIN":
                return new Admin();
            case "SUPERADMIN":
                return new SuperAdmin();
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
    }

}
