package com.capgemini.test.code.hexagonal.infrastructure.db.postgres.mapper;

import com.capgemini.test.code.hexagonal.domain.model.DNI;
import com.capgemini.test.code.hexagonal.domain.model.Email;
import com.capgemini.test.code.hexagonal.domain.model.ID;
import com.capgemini.test.code.hexagonal.domain.model.Name;
import com.capgemini.test.code.hexagonal.domain.model.Phone;
import com.capgemini.test.code.hexagonal.domain.model.User;
import com.capgemini.test.code.hexagonal.domain.model.role.Admin;
import com.capgemini.test.code.hexagonal.domain.model.role.Role;
import com.capgemini.test.code.hexagonal.domain.model.role.SuperAdmin;
import com.capgemini.test.code.hexagonal.infrastructure.db.postgres.entity.UserEntity;

public class UserMapper {

    public static User fromEntityToDomain(UserEntity entity) {
        return new User(
                new ID(entity.getId()),
                new Email(entity.getEmail()),
                new Name(entity.getName()),
                new DNI(entity.getDni()),
                new Phone(entity.getPhone()),
                mapToRole(entity.getRole()));
    }

    public static UserEntity fromDomainToEntity(User domain) {
        return new UserEntity(
                domain.getName().getValue(),
                domain.getEmail().getValue(),
                domain.getDni().getValue(),
                domain.getPhone().getValue(),
                domain.getRole().getName());
    }

    private static Role mapToRole(String role) {
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
