package com.capgemini.test.code.domain.entities.roles;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getName();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        switch (dbData.toUpperCase()) {
            case "USER":
                return new Admin();
            case "ADMIN":
                return new Admin();
            case "SUPERADMIN":
                return new SuperAdmin();
            default:
                throw new IllegalArgumentException("Unknown role: " + dbData);
        }
    }

}
